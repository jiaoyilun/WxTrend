package com.fisher.wxtrend.activity;

import android.support.v7.app.AppCompatActivity;

import com.fisher.wxtrend.ui.TabActionBarView;
import com.fisher.wxtrend.util.Constants;

/**
 * Created by Administrator on 2016/7/14/.
 */
public class BaseActivity extends AppCompatActivity {

    public void initTabView(TabActionBarView tabActionBarView, TabActionBarView.ITabActionCallback callback) {
        tabActionBarView.bindTab(callback, Constants.TAB_LEFT_TITLE, Constants.TAB_MIDDLE_TITLE, Constants.TAB_RIGHT_TITLE);
    }

}
