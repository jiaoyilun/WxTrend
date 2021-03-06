package com.fisher.wxtrend.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fisher.wxtrend.R;
import com.fisher.wxtrend.activity.ArticleDetailActivity;
import com.fisher.wxtrend.adapter.ArticleListAdapter;
import com.fisher.wxtrend.base.BaseFragment;
import com.fisher.wxtrend.http.HttpMethods;
import com.fisher.wxtrend.po.PageData;
import com.fisher.wxtrend.po.WxArticle;
import com.fisher.wxtrend.subscribers.ProgressSubscriber;
import com.fisher.wxtrend.subscribers.SubscriberOnNextListener;
import com.fisher.wxtrend.util.DataUtil;
import com.fisher.wxtrend.util.LogUtil;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Administrator on 2016/7/14/.
 */
public class ArticleListFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.view_list_article)
    RecyclerView articleListView;
    @BindView(R.id.id_swipe_ly)
    SwipeRefreshLayout swipeRefreshLayout;

    private SubscriberOnNextListener getDataOnNext;

    private int pageNum = 0;
    private List<WxArticle> totalList;

    private LinearLayoutManager mLayoutManager;

    private String currentTypeId;
    private final static String TYPEID = "TYPEID";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        currentTypeId = getArguments().getString(TYPEID);
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
                        Bundle bundle = new Bundle();
                        bundle.putParcelable("DATA", (WxArticle) obj);
                        intent.putExtras(bundle);
                        intent.setClass(getActivity(), ArticleDetailActivity.class);
                        startActivity(intent);
                    }
                });
            }
        };
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mLayoutManager = new LinearLayoutManager(getActivity());

        //        articleListView = findViewById(R.id.view_list_article);
        articleListView.setLayoutManager(mLayoutManager);

        initSwipeRefreshView();
        loadData(true);
    }


    private void initSwipeRefreshView() {
        //        swipeRefreshLayout = findViewById(R.id.id_swipe_ly);
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
        LogUtil.e(this, "pageNum:" + pageNum + "--------------" + "typeID:" + currentTypeId);
        HttpMethods.getInstance().getWxArticleList(new ProgressSubscriber(getDataOnNext, getActivity()), currentTypeId, "", "", String.valueOf(pageNum));
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
