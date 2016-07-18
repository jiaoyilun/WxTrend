package com.fisher.wxtrend.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/7/14/.
 */
public class ArticleListFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "ArticleListFragment";

    private View root;
    private RecyclerView articleListView;
    private SwipeRefreshLayout swipeRefreshLayout;

    private SubscriberOnNextListener getDataOnNext;

    private int pageNum = 0;
    private List<WxArticle> totalList;

    private LinearLayoutManager mLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_article_list, container, false);
        mLayoutManager = new LinearLayoutManager(getActivity());
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        totalList = new ArrayList<>();
        getDataOnNext = new SubscriberOnNextListener<PageData<LinkedTreeMap>>() {
            @Override
            public void onNext(PageData<LinkedTreeMap> pageData) {
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }

                List<WxArticle> dataList = DataUtil.convertObjToBean(pageData.getData(), WxArticle.class);
                totalList.addAll(dataList);

                ArticleListAdapter articleListAdapter = new ArticleListAdapter(getActivity(), totalList);
                articleListView.setAdapter(articleListAdapter);
                articleListView.addOnScrollListener(scrollListener);

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
        articleListView.setLayoutManager(mLayoutManager);

        initSwipeRefreshView();
        loadData(true);

    }

    private void initSwipeRefreshView() {
        swipeRefreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.id_swipe_ly);
        swipeRefreshLayout.setOnRefreshListener(this);
        // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_green_light, android.R.color.holo_orange_light, android.R.color.holo_red_light);
        //        swipeRefreshLayout.setDistanceToTriggerSync(400);// 设置手指在屏幕下拉多少距离会触发下拉刷新
        //        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.red);
        //        swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
    }


    @Override
    public void onRefresh() {
        loadData(true);
    }

    private void loadData(boolean isRefresh) {
        if (isRefresh) {
            pageNum = 0;
            totalList.clear();
        } else {
            pageNum = ++pageNum;
        }
        Log.d(TAG, pageNum + "");
        HttpMethods.getInstance().getWxArticleList(new ProgressSubscriber(getDataOnNext, getActivity()), "", "", "", String.valueOf(pageNum));
    }

    private RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            int lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();
            int totalItemCount = mLayoutManager.getItemCount();
            //lastVisibleItem >= totalItemCount - 4 表示剩下4个item自动加载，各位自由选择
            // dy>0 表示向下滑动
            if (lastVisibleItem >= totalItemCount - 4 && dy > 0) {
                /*if (isLoadingMore) {
                    Log.d(TAG, "ignore manually update!");
                } else {
                    loadPage();//这里多线程也要手动控制isLoadingMore
                    isLoadingMore = false;
                }*/
                loadData(false);
            }
        }
    };


}
