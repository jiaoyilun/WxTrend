package com.fisher.wxtrend.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;

import com.fisher.wxtrend.R;
import com.fisher.wxtrend.base.BaseActivity;
import com.fisher.wxtrend.fragment.ArticleFragment;
import com.fisher.wxtrend.fragment.NumFragment;
import com.fisher.wxtrend.ui.TabActionBarView;

public class MainActivity extends BaseActivity implements TabActionBarView.ITabActionCallback {

    private TabActionBarView tabActionBarView;

    private Fragment currentFragment;

    private ArticleFragment articleFragment;

    private NumFragment numFragment;

    private int resId = R.id.fragment_tab_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        initFragment();
    }


    @Override
    public void onBackPressed() {
       /* if (null != typePopWin && typePopWin.isShowing()) {
            typePopWin.dismiss();
        } else {
            super.onBackPressed();
        }*/
    }

    @Override
    public void onLeftTabClick() {
        if (articleFragment == null) {
            articleFragment = new ArticleFragment();
        }
        switchFragment(currentFragment, articleFragment, resId);
        currentFragment = articleFragment;
    }

    @Override
    public void onMiddleTabClick() {

    }

    @Override
    public void onRightClick() {
        if (numFragment == null) {
            numFragment = new NumFragment();
        }
        switchFragment(currentFragment, numFragment, resId);
        currentFragment = numFragment;
    }

    private void initFragment() {
        articleFragment = new ArticleFragment();
        switchFragment(currentFragment, articleFragment, resId);
        currentFragment = articleFragment;
    }

}

