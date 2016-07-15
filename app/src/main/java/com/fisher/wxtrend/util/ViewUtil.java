package com.fisher.wxtrend.util;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Administrator on 2016/7/15/.
 */
public class ViewUtil {

    public static final void displayImg(Context context, String url, ImageView destView) {
        Picasso.with(context).load(url).resize(120, 100).centerCrop().into(destView);
    }

}
