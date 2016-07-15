package com.fisher.wxtrend.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fisher.wxtrend.po.WxArticle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/14/.
 */
public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.RecVH> {
    private List<WxArticle> articleList = new ArrayList<>();


    @Override
    public RecVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RecVH extends RecyclerView.ViewHolder {
        ImageView iv_logo;
        TextView tv_nu;
        TextView tv_time;
        TextView tv_context;

        public RecVH(View itemView) {
            super(itemView);
        }
    }

}
