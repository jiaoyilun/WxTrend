package com.fisher.wxtrend.fragment;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.fisher.wxtrend.R;
import com.fisher.wxtrend.adapter.GridViewSortAdapter;
import com.fisher.wxtrend.adapter.TabPagerAdapter;
import com.fisher.wxtrend.base.BaseFragment;
import com.fisher.wxtrend.http.HttpMethods;
import com.fisher.wxtrend.po.WxType;
import com.fisher.wxtrend.subscribers.ProgressSubscriber;
import com.fisher.wxtrend.subscribers.SubscriberOnNextListener;
import com.fisher.wxtrend.ui.DragSortGridView;
import com.fisher.wxtrend.util.DataUtil;
import com.fisher.wxtrend.util.LogUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeixinFragment extends BaseFragment {

    @BindView(R.id.tabs)
    TabLayout mTabs;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    private View view;
    private TabPagerAdapter pagerAdapter;
    private List<WxType> typeList;
    private PopupWindow typeManagerView;
    private DragSortGridView mGridView;
    private GridViewSortAdapter mAdapter;
    private Menu mMenu;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_weixin, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    private void initData() {
        SubscriberOnNextListener getDataOnNext = new SubscriberOnNextListener<List<WxType>>() {
            @Override
            public void onNext(List<WxType> dataList) {
                typeList = dataList;
                initView();
            }
        };
        HttpMethods.getInstance().getWxArticleType(new ProgressSubscriber(getDataOnNext, getContext()));
    }

    private void initView() {
        pagerAdapter = new TabPagerAdapter(getFragmentManager(), getContext(), typeList);
        mViewPager.setAdapter(pagerAdapter);
        mTabs.setupWithViewPager(mViewPager);
        int tabStyle = typeList.size() > 5 ? TabLayout.MODE_SCROLLABLE : TabLayout.MODE_FIXED;
        mTabs.setTabMode(tabStyle);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        this.mMenu = menu;
        inflater.inflate(R.menu.menu_fragment_weixin, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_manager_type:
                showTypeManager();
                break;
            default:
                break;
        }
        return true;
    }

    private void showTypeManager() {
        toggleMenuVisible();//菜单点击之后，弹出层，隐藏菜单

        if (typeManagerView == null) {
            View popWinlayout = LayoutInflater.from(getContext()).inflate(R.layout.layout_popup_type, null);

            int width = view.getWidth();
            int height = view.getHeight();
            if (typeManagerView == null) {
                typeManagerView = new PopupWindow(popWinlayout, width, height);
                ColorDrawable dw = new ColorDrawable(0xFFFFFFFF);
                typeManagerView.setBackgroundDrawable(dw);
                typeManagerView.setAnimationStyle(R.style.PopupAnimation);

                //                typeCloseBtn = ButterKnife.findById(popWinlayout, R.id.btn_close);
                //                typeCloseBtn.setOnClickListener(new View.OnClickListener() {
                //                    @Override
                //                    public void onClick(View v) {
                //                        typePopWin.dismiss();//PopUpWindow只有打开是才会触发
                //                    }
                //                });
            }

            mGridView = ButterKnife.findById(popWinlayout, R.id.activity_grid_view_sort_main);
            mAdapter = new GridViewSortAdapter(mGridView, getContext(), DataUtil.convertTypeToStr(typeList));
            mGridView.setAdapter(mAdapter);

        }
        typeManagerView.showAtLocation(mViewPager, Gravity.BOTTOM, 0, 0);

    }

    void closeTypeManager() {
        LogUtil.e(this, "close");
        if (null != typeManagerView && typeManagerView.isShowing()) {
            typeManagerView.dismiss();
        }
    }

    private void toggleMenuVisible() {
        if (null == mMenu)
            return;

        boolean disabled = mMenu.hasVisibleItems() ? false : true;
        for (int i = 0; i < mMenu.size(); i++) {
            mMenu.getItem(i).setVisible(disabled);
            mMenu.getItem(i).setEnabled(disabled);
        }
    }

}
