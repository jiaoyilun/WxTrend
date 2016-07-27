package com.fisher.wxtrend.fragment;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.fisher.wxtrend.R;
import com.fisher.wxtrend.adapter.GridViewSortAdapter;
import com.fisher.wxtrend.adapter.TabAdapter;
import com.fisher.wxtrend.base.BaseFragment;
import com.fisher.wxtrend.http.HttpMethods;
import com.fisher.wxtrend.po.WxType;
import com.fisher.wxtrend.subscribers.ProgressSubscriber;
import com.fisher.wxtrend.subscribers.SubscriberOnNextListener;
import com.fisher.wxtrend.ui.DragSortGridView;
import com.fisher.wxtrend.util.DataUtil;
import com.fisher.wxtrend.util.LogUtil;
import com.viewpagerindicator.TabPageIndicator;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/19/.
 */
public class ArticleFragment extends BaseFragment {
    @BindView(R.id.page_indicator)
    TabPageIndicator mPageIndicator;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    @BindView(R.id.layout_page)
    RelativeLayout pageLayout;
    @BindView(R.id.btn_open)
    ImageView typeOpenBtn;

    private ImageView typeCloseBtn;
    private FragmentPagerAdapter fragPagerAdapter;
    private PopupWindow typePopWin;
    private DragSortGridView mGridView;
    private GridViewSortAdapter mAdapter;
    private List<WxType> typeList;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_tabpage, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initView();
    }

    public void initView() {
        //        pageLayout = findViewById(R.id.layout_page);
        //        mPageIndicator = findViewById(R.id.page_indicator);
        //        mViewPager = findViewById(R.id.view_pager);

        SubscriberOnNextListener getDataOnNext = new SubscriberOnNextListener<List<WxType>>() {
            @Override
            public void onNext(List<WxType> dataList) {
                LogUtil.e(this, "onNext");

                typeList = dataList;

                fragPagerAdapter = new TabAdapter(getFragmentManager(), typeList);
                mViewPager.setAdapter(fragPagerAdapter);
                mPageIndicator.setViewPager(mViewPager, 0);

                //                typeOpenBtn = findViewById(R.id.btn_open);
                typeOpenBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showTypePopWin();
                    }
                });

            }
        };

        HttpMethods.getInstance().getWxArticleType(new ProgressSubscriber(getDataOnNext, getContext()));
    }


    private void showTypePopWin() {
        if (typePopWin == null) {
            LogUtil.e(this, "popupWin init");
            View popWinlayout = LayoutInflater.from(getContext()).inflate(R.layout.layout_popup_type, null);

            int width = mViewPager.getWidth();
            int height = pageLayout.getHeight();
            if (typePopWin == null) {
                typePopWin = new PopupWindow(popWinlayout, width, height);
                ColorDrawable dw = new ColorDrawable(0xFFFFFFFF);
                typePopWin.setBackgroundDrawable(dw);
                typePopWin.setAnimationStyle(R.style.PopupAnimation);

               /* typeCloseBtn = ButterKnife.findById(popWinlayout, R.id.btn_close);
                typeCloseBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        typePopWin.dismiss();//PopUpWindow只有打开是才会触发
                    }
                });*/
            }

            mGridView = ButterKnife.findById(popWinlayout, R.id.activity_grid_view_sort_main);
            mAdapter = new GridViewSortAdapter(mGridView, getContext(), DataUtil.convertTypeToStr(typeList));
            mGridView.setAdapter(mAdapter);

        }

        typePopWin.showAtLocation(mViewPager, Gravity.BOTTOM, 0, 0);
    }


}
