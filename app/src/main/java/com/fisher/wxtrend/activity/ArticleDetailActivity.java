package com.fisher.wxtrend.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
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
import butterknife.OnClick;

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
    @BindView(R.id.layout_collapsingToolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;

    private final static String DATA = "DATA";
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
        //        collapsingToolbarLayout.setTitle(article.getTitle());


        initView();
    }

    private void initView() {
        ViewUtil.displayImg(ArticleDetailActivity.this, article.getContentImg(), contentImg, 200, 188);
        tv_title.setText(article.getTitle());
        tv_source.setText(article.getUserName());

        mWebView.getSettings().setJavaScriptEnabled(false);
        mWebView.loadUrl(article.getUrl());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_article_detail, menu);
        return true;
    }

    @OnClick(R.id.btn_share)
    void share() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Share");
        intent.putExtra(Intent.EXTRA_TEXT, "I have successfully share my message through my app");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(Intent.createChooser(intent, getTitle()));
    }

}
