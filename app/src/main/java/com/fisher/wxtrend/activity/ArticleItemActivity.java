package com.fisher.wxtrend.activity;

import android.os.Bundle;

import com.fisher.wxtrend.R;
import com.fisher.wxtrend.base.BaseActivity;
import com.fisher.wxtrend.ui.ProgressWebView;

/**
 * Created by Administrator on 2016/7/15/.
 */
public class ArticleItemActivity extends BaseActivity {
    private ProgressWebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_item);

        String url = getIntent().getStringExtra("url");

        mWebView = (ProgressWebView) findViewById(R.id.view_article);
        mWebView.getSettings().setJavaScriptEnabled(false);
        mWebView.loadUrl(url);
    }

}
