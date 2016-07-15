package com.fisher.wxtrend.po;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016/7/14/.
 */
public class ResponseType {

    @SerializedName("ret_code")
    private String code;

    @SerializedName("typeList")
    private List<WxType> typeList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<WxType> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<WxType> typeList) {
        this.typeList = typeList;
    }
}
