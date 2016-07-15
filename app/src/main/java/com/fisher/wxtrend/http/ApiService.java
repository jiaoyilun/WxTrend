package com.fisher.wxtrend.http;

import com.fisher.wxtrend.po.ApiResult;
import com.fisher.wxtrend.po.PageBean;
import com.fisher.wxtrend.po.ResponseType;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/6/30/.
 */
public interface ApiService {

    @GET("weixin_num_list")
    Observable<ApiResult<PageBean>> getWxNumList(@Header("apiKey") String apiKey, @Query("type1_id") String type1, @Query("type2_id") String type2, @Query("keyword") String keyword, @Query("page") String page);


    @GET("weixin_article_list")
    Observable<ApiResult<PageBean>> getWxArticleList(@Header("apiKey") String apiKey, @Query("typeId") String typeId, @Query("type2_id") String type2, @Query("keyword") String keyword, @Query("page") String page);

    @GET("winxin_num_type")
    Observable<ApiResult> getWxNumType(@Header("apiKey") String apiKey);

    @GET("weixin_article_type")
    Observable<ApiResult<ResponseType>> getWxArticleType(@Header("apiKey") String apiKey);

}
