package com.fisher.wxtrend.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.fisher.wxtrend.fragment.ArticleListFragment;
import com.fisher.wxtrend.po.WxType;

import java.util.List;

/**
 * Created by Administrator on 2016/7/27/.
 */
public class TabPagerAdapter extends android.support.v4.app.FragmentPagerAdapter {

    private Context context;

    private List<WxType> typeList;

    public TabPagerAdapter(FragmentManager fm, Context context, List<WxType> typeList) {
        super(fm);
        this.context = context;
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
    public int getCount() {
        return typeList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return typeList.get(position).getName();
    }
}
