package com.fisher.wxtrend.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.fisher.wxtrend.R;
import com.fisher.wxtrend.ui.TabActionBarView;

public class Main2Activity extends BaseActivity implements TabActionBarView.ITabActionCallback {

    private TabActionBarView tabActionBarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabActionBarView = (TabActionBarView) findViewById(R.id.tabActionBar);
        tabActionBarView.bindTab(this, "热门文章", "热门公号");

    }

    @Override
    public void onLeftTabClick() {
        Toast.makeText(Main2Activity.this, "Left Click", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onMiddleTabClick() {
        Toast.makeText(Main2Activity.this, "Middle Click", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRightClick() {
        Toast.makeText(Main2Activity.this, "Right Click", Toast.LENGTH_SHORT).show();
    }
}
