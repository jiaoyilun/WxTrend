package com.fisher.wxtrend.po;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/7/14/.
 */
public class WxUser {

    @SerializedName("code2img")
    private String code2img;
    @SerializedName("id")
    private String id;
    @SerializedName("pubNum")
    private String pubNum;
    @SerializedName("tag")
    private String tag;
    @SerializedName("type1_id")
    private String type1_id;
    @SerializedName("type1_name")
    private String type1_name;
    @SerializedName("type2_id")
    private String type2_id;
    @SerializedName("type2_name")
    private String type2_name;
    @SerializedName("userLogo")
    private String userLogo;
    @SerializedName("weiNum")
    private String weiNum;

    public String getCode2img() {
        return code2img;
    }

    public void setCode2img(String code2img) {
        this.code2img = code2img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPubNum() {
        return pubNum;
    }

    public void setPubNum(String pubNum) {
        this.pubNum = pubNum;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getType1_id() {
        return type1_id;
    }

    public void setType1_id(String type1_id) {
        this.type1_id = type1_id;
    }

    public String getType1_name() {
        return type1_name;
    }

    public void setType1_name(String type1_name) {
        this.type1_name = type1_name;
    }

    public String getType2_id() {
        return type2_id;
    }

    public void setType2_id(String type2_id) {
        this.type2_id = type2_id;
    }

    public String getType2_name() {
        return type2_name;
    }

    public void setType2_name(String type2_name) {
        this.type2_name = type2_name;
    }

    public String getUserLogo() {
        return userLogo;
    }

    public void setUserLogo(String userLogo) {
        this.userLogo = userLogo;
    }

    public String getWeiNum() {
        return weiNum;
    }

    public void setWeiNum(String weiNum) {
        this.weiNum = weiNum;
    }

}
