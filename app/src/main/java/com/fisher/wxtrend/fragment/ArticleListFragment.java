package com.fisher.wxtrend.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fisher.wxtrend.R;
import com.fisher.wxtrend.activity.ArticleItemActivity;
import com.fisher.wxtrend.adapter.ArticleListAdapter;
import com.fisher.wxtrend.http.HttpMethods;
import com.fisher.wxtrend.po.PageData;
import com.fisher.wxtrend.po.WxArticle;
import com.fisher.wxtrend.subscribers.ProgressSubscriber;
import com.fisher.wxtrend.subscribers.SubscriberOnNextListener;
import com.fisher.wxtrend.util.DataUtil;
import com.google.gson.internal.LinkedTreeMap;

import java.util.List;


/**
 * Created by Administrator on 2016/7/14/.
 */
public class ArticleListFragment extends Fragment {
    private static final String TAG = "ArticleListFragment";

    private View root;
    private RecyclerView articleListView;

    private SubscriberOnNextListener getDataOnNext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_article_list, container, false);
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getDataOnNext = new SubscriberOnNextListener<PageData<LinkedTreeMap>>() {
            @Override
            public void onNext(PageData<LinkedTreeMap> pageData) {
                List<WxArticle> dataList = DataUtil.convertObjToBean(pageData.getData(), WxArticle.class);

                ArticleListAdapter articleListAdapter = new ArticleListAdapter(getActivity(), dataList);
                articleListView.setAdapter(articleListAdapter);

                articleListAdapter.setmOnItemClickListener(new ArticleListAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, Object obj) {
                        Intent intent = new Intent();
                        intent.putExtra("url", String.valueOf(obj));
                        intent.setClass(getActivity(), ArticleItemActivity.class);
                        startActivity(intent);
                    }
                });

            }
        };

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        articleListView = (RecyclerView) root.findViewById(R.id.view_list_article);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        articleListView.setLayoutManager(linearLayoutManager);

        HttpMethods.getInstance().getWxArticleList(new ProgressSubscriber(getDataOnNext, getActivity()), "", "", "", "");
    }

}
