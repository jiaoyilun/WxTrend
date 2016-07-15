package com.fisher.wxtrend.po;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/7/14/.
 */
public class PageBean<T> {
    @SerializedName("ret_code")
    private String code;

    @SerializedName("pagebean")
    private PageData<T> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public PageData<T> getData() {
        return data;
    }

    public void setData(PageData<T> data) {
        this.data = data;
    }
}
