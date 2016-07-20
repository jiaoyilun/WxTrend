package com.fisher.wxtrend.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fisher.wxtrend.R;
import com.fisher.wxtrend.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/19/.
 */
public class NumFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_num, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
