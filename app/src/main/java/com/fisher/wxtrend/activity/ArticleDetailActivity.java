package com.fisher.wxtrend.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.fisher.wxtrend.R;
import com.fisher.wxtrend.base.BaseActivity;
import com.fisher.wxtrend.po.WxArticle;
import com.fisher.wxtrend.ui.ProgressWebView;
import com.fisher.wxtrend.util.ViewUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/15/.
 */
public class ArticleDetailActivity extends BaseActivity {
    @BindView(R.id.view_article)
    ProgressWebView mWebView;
    @BindView(R.id.iv_img)
    ImageView contentImg;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.tv_source)
    TextView tv_source;
    @BindView(R.id.toolbar_detail)
    Toolbar mToolbar;

    private final static String DATA = "data";
    private WxArticle article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail);
        ButterKnife.bind(this);

        article = getIntent().getParcelableExtra(DATA);

        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//设置是否有返回箭头


        initView();
    }

    private void initView() {
        ViewUtil.displayImg(ArticleDetailActivity.this, article.getContentImg(), contentImg, 200, 188);
        tv_title.setText(article.getTitle());
        tv_source.setText(article.getUserName());

        mWebView.getSettings().setJavaScriptEnabled(false);
        mWebView.loadUrl(article.getUrl());
    }

    static public Intent newIntent(Context context, Bundle bundle) {
        Intent newIntent = new Intent(context, ArticleDetailActivity.class);
        newIntent.putExtra(DATA, bundle);
        return newIntent;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_article_detail, menu);
        return true;
    }
}
