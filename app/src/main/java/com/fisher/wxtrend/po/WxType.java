package com.fisher.wxtrend.po;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/7/14/.
 */
public class WxType {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
