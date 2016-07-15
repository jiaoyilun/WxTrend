package com.fisher.wxtrend.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;

import com.fisher.wxtrend.R;
import com.fisher.wxtrend.adapter.TabAdapter;
import com.viewpagerindicator.TabPageIndicator;

public class MainActivity extends FragmentActivity {
    private TabPageIndicator mPageIndicator;
    private ViewPager mViewPager;
    private FragmentPagerAdapter fragPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();

        fragPagerAdapter = new TabAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(fragPagerAdapter);
        mPageIndicator.setViewPager(mViewPager, 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void initUI() {
        mPageIndicator = (TabPageIndicator) findViewById(R.id.page_indicator);
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
    }

}

