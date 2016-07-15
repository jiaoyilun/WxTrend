package com.fisher.wxtrend.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fisher.wxtrend.R;
import com.fisher.wxtrend.po.WxArticle;
import com.fisher.wxtrend.util.ViewUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/14/.
 */
public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.RecVH> implements View.OnClickListener {

    private Context context;
    private List<WxArticle> articleList = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    public ArticleListAdapter(Context context, List<WxArticle> articleList) {
        this.articleList = articleList;
        this.context = context;
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public RecVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_article, parent, false);
        view.setOnClickListener(this);
        return new RecVH(view);
    }

    @Override
    public void onBindViewHolder(RecVH holder, int position) {
        WxArticle article = articleList.get(position);
        holder.news_item_title.setText(article.getTitle());
        ViewUtil.displayImg(context, article.getContentImg(), holder.news_item_icon);


        holder.itemView.setTag(article.getUrl());
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, v.getTag());
        }
    }

    public class RecVH extends RecyclerView.ViewHolder {
        ImageView news_item_icon;
        TextView news_item_title;

        public RecVH(View itemView) {
            super(itemView);
            news_item_icon = (ImageView) itemView.findViewById(R.id.news_item_icon);
            news_item_title = (TextView) itemView.findViewById(R.id.news_item_title);
        }
    }


    public interface OnItemClickListener {
        void onItemClick(View view, Object obj);
    }

}
