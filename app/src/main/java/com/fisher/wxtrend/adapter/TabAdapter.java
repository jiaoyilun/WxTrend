package com.fisher.wxtrend.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.fisher.wxtrend.fragment.ArticleListFragment;
import com.fisher.wxtrend.util.Constants;

public class TabAdapter extends FragmentPagerAdapter {

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        ArticleListFragment fragment = new ArticleListFragment();
        Bundle b = new Bundle();
        String title = Constants.TITLES[position];
        b.putString("TITLES", title);
        fragment.setArguments(b);
        return fragment;
    }



    @Override
    public CharSequence getPageTitle(int position) {
        return Constants.TITLES[position];
    }

    @Override
    public int getCount() {
        return Constants.TITLES.length;
    }

}  