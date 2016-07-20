package com.fisher.wxtrend.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.fisher.wxtrend.ui.TabActionBarView;
import com.fisher.wxtrend.util.Constants;
import com.fisher.wxtrend.util.LogUtil;

/**
 * Created by Administrator on 2016/7/14/.
 */
public class BaseActivity extends AppCompatActivity {

    public void initTabView(TabActionBarView tabActionBarView, TabActionBarView.ITabActionCallback callback) {
        tabActionBarView.bindTab(callback, Constants.TAB_LEFT_TITLE, Constants.TAB_MIDDLE_TITLE, Constants.TAB_RIGHT_TITLE);
    }

    public void switchFragment(Fragment from, Fragment to, int resId) {
        if (to == null)
            return;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (from == null) {//初始化,no hide
            LogUtil.e(this, "init~~~~~~~~~~");
            transaction.add(resId, to).commit();
            return;
        }
        if (!to.isAdded()) {
            LogUtil.e(this, "switch~~~~add");
            transaction.hide(from).add(resId, to).commit();
        } else {
            LogUtil.e(this, "switch~~~~show");
            transaction.hide(from).show(to).commit();
        }
    }
}
