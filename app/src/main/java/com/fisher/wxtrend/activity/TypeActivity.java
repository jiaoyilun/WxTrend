package com.fisher.wxtrend.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.fisher.wxtrend.R;
import com.fisher.wxtrend.adapter.GridViewSortAdapter;
import com.fisher.wxtrend.http.HttpMethods;
import com.fisher.wxtrend.po.WxType;
import com.fisher.wxtrend.subscribers.ProgressSubscriber;
import com.fisher.wxtrend.subscribers.SubscriberOnNextListener;
import com.fisher.wxtrend.ui.DragSortGridView;
import com.fisher.wxtrend.util.DataUtil;

import java.util.List;

/**
 * Created by Administrator on 2016/7/15/.
 */
public class TypeActivity extends BaseActivity {

    private DragSortGridView mGridView;
    private GridViewSortAdapter mAdapter;
    private List<String> titles;
    private SubscriberOnNextListener getDataOnNext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type);

        mGridView = (DragSortGridView) findViewById(R.id.activity_grid_view_sort_main);

        getDataOnNext = new SubscriberOnNextListener<List<WxType>>() {
            @Override
            public void onNext(List<WxType> dataList) {
                titles = DataUtil.convertTypeToStr(dataList);

                mAdapter = new GridViewSortAdapter(mGridView, TypeActivity.this, titles);
                mGridView.setAdapter(mAdapter);

                mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(TypeActivity.this, titles.get(position), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        HttpMethods.getInstance().getWxArticleType(new ProgressSubscriber(getDataOnNext, TypeActivity.this));
    }


}
