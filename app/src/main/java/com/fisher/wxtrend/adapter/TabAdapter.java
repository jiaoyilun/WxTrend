package com.fisher.wxtrend.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.fisher.wxtrend.fragment.ArticleListFragment;
import com.fisher.wxtrend.po.WxType;

import java.util.List;

public class TabAdapter extends FragmentPagerAdapter {

    private List<WxType> typeList;

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    public TabAdapter(FragmentManager fm, List<WxType> typeList) {
        super(fm);
        this.typeList = typeList;
    }

    @Override
    public Fragment getItem(int position) {
        ArticleListFragment fragment = new ArticleListFragment();
        Bundle b = new Bundle();
        WxType wxType = typeList.get(position);
        b.putString("TYPEID", wxType.getId());
        b.putString("TYPENAME", wxType.getName());
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return typeList.get(position).getName();
    }

    @Override
    public int getCount() {
        return typeList.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }
}