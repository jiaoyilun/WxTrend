package com.fisher.wxtrend.base;

import android.support.v4.app.Fragment;

public abstract class BaseFragment extends Fragment {

   /* *//**
     * 视图对象
     *//*
    public View mFragmentViewObject;

    *//**
     * 布局文件ID
     *//*
    protected abstract int getLayoutId();

    *//**
     * 找到元素
     *
     * @param id
     * @param <T>
     * @return
     *//*
    protected <T extends View> T findViewById(@IdRes int id) {
        return (T) mFragmentViewObject.findViewById(id);
    }

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mFragmentViewObject == null) {
            mFragmentViewObject = inflater.inflate(getLayoutId(), container, false);
        }
        return mFragmentViewObject;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (mFragmentViewObject == null)
            return;

        ViewGroup mParent = (ViewGroup) mFragmentViewObject.getParent();

        if (mParent == null)
            return;

        mParent.removeView(mFragmentViewObject);
    }*/
}