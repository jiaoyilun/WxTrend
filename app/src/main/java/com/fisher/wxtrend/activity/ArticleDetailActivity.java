package com.fisher.wxtrend.activity;

import android.os.Bundle;
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

    private WxArticle article;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_item);
        ButterKnife.bind(this);

        article = getIntent().getParcelableExtra("DATA");

        initView();
    }

    private void initView() {

        ViewUtil.displayImg(ArticleDetailActivity.this, article.getContentImg(), contentImg, 200, 188);
        tv_title.setText(article.getTitle());
        tv_source.setText(article.getUserName());

        mWebView.getSettings().setJavaScriptEnabled(false);
        mWebView.loadUrl(article.getUrl());

    }

}
