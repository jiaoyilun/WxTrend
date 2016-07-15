package com.fisher.wxtrend.http;

import com.fisher.wxtrend.po.ApiResult;
import com.fisher.wxtrend.po.PageBean;
import com.fisher.wxtrend.po.PageData;
import com.fisher.wxtrend.po.ResponseType;
import com.fisher.wxtrend.po.WxArticle;
import com.fisher.wxtrend.po.WxType;
import com.fisher.wxtrend.util.Constants;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/6/30/.
 */
public class HttpMethods {
    private static final int DEFAULT_TIMEOUT = 5;
    private Retrofit retrofit;
    private ApiService apiService;
    private String KEY = Constants.API_KEY;


    private HttpMethods() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder().client(httpClientBuilder.addInterceptor(logging).build()).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).baseUrl(Constants.API_URL).build();

        apiService = retrofit.create(ApiService.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    //获取单例
    public static HttpMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }


    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
        o.subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(s);
    }

    public void getWxNumList(Subscriber<ApiResult<PageBean>> subscriber, String type1, String type2, String keyword, String page) {
        Observable observable = apiService.getWxNumList(KEY, type1, type2, keyword, page);
        toSubscribe(observable, subscriber);
    }

    public void getWxArticleList(Subscriber<PageData<WxArticle>> subscriber, String type1, String type2, String keyword, String page) {
        Observable observable = apiService.getWxArticleList(KEY, type1, type2, keyword, page).map(new Func1<ApiResult, PageData<WxArticle>>() {
            @Override
            public PageData<WxArticle> call(ApiResult responseResult) {
                PageBean pageBean = (PageBean) responseResult.getBody();
                PageData<WxArticle> list = pageBean.getData();
                return list;
            }
        });
        toSubscribe(observable, subscriber);
    }

    public void getWxNumType(Subscriber<ApiResult<PageBean>> subscriber) {
        Observable observable = apiService.getWxNumType(KEY);
        toSubscribe(observable, subscriber);
    }

    public void getWxArticleType(Subscriber<List<WxType>> subscriber) {
        Observable observable = apiService.getWxArticleType(KEY).map(new Func1<ApiResult<ResponseType>, List<WxType>>() {
            @Override
            public List<WxType> call(ApiResult<ResponseType> responseTypeApiResult) {
                List<WxType> typeList = responseTypeApiResult.getBody().getTypeList();
                return typeList;
            }
        });
        toSubscribe(observable, subscriber);
    }
}
