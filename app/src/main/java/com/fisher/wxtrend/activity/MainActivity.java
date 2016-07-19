package com.fisher.wxtrend.activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.fisher.wxtrend.R;
import com.fisher.wxtrend.adapter.GridViewSortAdapter;
import com.fisher.wxtrend.adapter.TabAdapter;
import com.fisher.wxtrend.http.HttpMethods;
import com.fisher.wxtrend.po.WxType;
import com.fisher.wxtrend.subscribers.ProgressSubscriber;
import com.fisher.wxtrend.subscribers.SubscriberOnNextListener;
import com.fisher.wxtrend.ui.DragSortGridView;
import com.fisher.wxtrend.ui.TabActionBarView;
import com.fisher.wxtrend.util.DataUtil;
import com.viewpagerindicator.TabPageIndicator;

import java.util.List;

public class MainActivity extends BaseActivity implements TabActionBarView.ITabActionCallback {
    private TabPageIndicator mPageIndicator;
    private ViewPager mViewPager;
    private FragmentPagerAdapter fragPagerAdapter;
    private RelativeLayout pageLayout;

    private TabActionBarView tabActionBarView;

    private ImageView typeOpenBtn;
    private ImageView typeCloseBtn;

    private PopupWindow typePopWin;
    private DragSortGridView mGridView;
    private GridViewSortAdapter mAdapter;
    private List<WxType> typeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initUI();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void initUI() {
        tabActionBarView = (TabActionBarView) findViewById(R.id.tabActionBar);
        initTabView(tabActionBarView, this);

        pageLayout = (RelativeLayout) findViewById(R.id.layout_page);
        mPageIndicator = (TabPageIndicator) findViewById(R.id.page_indicator);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);

        SubscriberOnNextListener getDataOnNext = new SubscriberOnNextListener<List<WxType>>() {
            @Override
            public void onNext(List<WxType> dataList) {
                typeList = dataList;

                fragPagerAdapter = new TabAdapter(getSupportFragmentManager(), typeList);
                mViewPager.setAdapter(fragPagerAdapter);
                mPageIndicator.setViewPager(mViewPager, 0);

                typeOpenBtn = (ImageView) findViewById(R.id.btn_open);
                typeOpenBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showTypePopWin();
                    }
                });

            }
        };

        HttpMethods.getInstance().getWxArticleType(new ProgressSubscriber(getDataOnNext, MainActivity.this));
    }

    private void showTypePopWin() {
        if (typePopWin == null) {
            View popWinlayout = LayoutInflater.from(this).inflate(R.layout.layout_type, null);

            int width = mViewPager.getWidth();
            int height = pageLayout.getHeight();
            typePopWin = new PopupWindow(popWinlayout, width, height);

            mGridView = (DragSortGridView) popWinlayout.findViewById(R.id.activity_grid_view_sort_main);
            mAdapter = new GridViewSortAdapter(mGridView, MainActivity.this, DataUtil.convertTypeToStr(typeList));
            mGridView.setAdapter(mAdapter);

            ColorDrawable dw = new ColorDrawable(0xFFFFFFFF);
            typePopWin.setBackgroundDrawable(dw);
            typePopWin.setAnimationStyle(R.style.PopupAnimation);

            typeCloseBtn = (ImageView) popWinlayout.findViewById(R.id.btn_close);
            typeCloseBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    typePopWin.dismiss();//PopUpWindow只有打开是才会触发
                }
            });
        }

        typePopWin.showAtLocation(mViewPager, Gravity.BOTTOM, 0, 0);
    }


    @Override
    public void onBackPressed() {
        if (null != typePopWin && typePopWin.isShowing()) {
            typePopWin.dismiss();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onLeftTabClick() {

    }

    @Override
    public void onMiddleTabClick() {

    }

    @Override
    public void onRightClick() {

    }
}

