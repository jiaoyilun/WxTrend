package com.fisher.wxtrend.util;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import com.fisher.wxtrend.ui.BadgeView;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

/**
 * Created by Administrator on 2016/7/15/.
 */
public class ViewUtil {

    public static final void displayImg(Context context, String url, ImageView destView, int width, int height) {
        RequestCreator creator = Picasso.with(context).load(url);
        if (width * height > 0) {
            creator.resize(width, height);
        }
        creator.centerCrop().memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).into(destView);
    }

    public static final BadgeView createBadgeView(Context context, View targetView) {
        BadgeView badgeView = new BadgeView(context);
        badgeView.setBadgeCount(2);
        badgeView.setBadgeGravity(Gravity.LEFT | Gravity.TOP);
        badgeView.setBadgeMargin(-3, -3, 0, 0);
        badgeView.setTargetView(targetView);
        return badgeView;
    }

}
