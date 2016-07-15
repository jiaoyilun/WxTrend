package com.fisher.wxtrend.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class TypeFragment extends Fragment {
    private static final String TAG = "TypeFragment";

    private View root;
    private DragSortGridView mGridView;
    private GridViewSortAdapter mAdapter;
    private List<String> titles;
    private SubscriberOnNextListener getDataOnNext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_type, container, false);
        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onActivityCreated");

        super.onActivityCreated(savedInstanceState);
        mGridView = (DragSortGridView) root.findViewById(R.id.activity_grid_view_sort_main);

        HttpMethods.getInstance().getWxArticleType(new ProgressSubscriber(getDataOnNext, getActivity()));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getDataOnNext = new SubscriberOnNextListener<List<WxType>>() {
            @Override
            public void onNext(List<WxType> dataList) {
                titles = DataUtil.convertTypeToStr(dataList);

                mAdapter = new GridViewSortAdapter(mGridView, getActivity(), titles);
                mGridView.setAdapter(mAdapter);

                mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getActivity(), titles.get(position), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
    }
}
