package com.fisher.wxtrend.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fisher.wxtrend.R;
import com.fisher.wxtrend.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/28/.
 */
public class WzFragment extends BaseFragment {

    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_wz, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    private void initData() {

    }
}
