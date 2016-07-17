package com.fisher.wxtrend.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.fisher.wxtrend.R;

public class ArticleTypeView extends TextView {
    private Context mContext;

    private boolean showIcon;

    public ArticleTypeView(Context context) {
        super(context);
        mContext = context;
        init(null, 0);
    }

    public ArticleTypeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(attrs, 0);
    }

    public ArticleTypeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.ArticleTypeView, defStyle, 0);

        showIcon = a.getBoolean(R.styleable.ArticleTypeView_showIcon,false);
        a.recycle();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(showIcon){
            drawBadge(this);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void drawBadge(View view){
        BadgeView badgeView = new BadgeView(mContext);
        badgeView.setBadgeCount(2);
        badgeView.setBadgeGravity(Gravity.LEFT|Gravity.TOP);
        badgeView.setBadgeMargin(-3,-3,0,0);
        badgeView.setTargetView(view);
    }
}
