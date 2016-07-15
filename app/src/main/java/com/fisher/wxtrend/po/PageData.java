package com.fisher.wxtrend.po;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016/7/14/.
 */
public class PageData<T> {

    @SerializedName("allNum")
    private int allNums;
    @SerializedName("allPages")
    private int allPages;
    @SerializedName("currentPage")
    private int currentPage;
    @SerializedName("maxResult")
    private int maxResult;
    @SerializedName("contentlist")
    private List<T> data;

    public int getAllNums() {
        return allNums;
    }

    public void setAllNums(int allNums) {
        this.allNums = allNums;
    }

    public int getAllPages() {
        return allPages;
    }

    public void setAllPages(int allPages) {
        this.allPages = allPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getMaxResult() {
        return maxResult;
    }

    public void setMaxResult(int maxResult) {
        this.maxResult = maxResult;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
