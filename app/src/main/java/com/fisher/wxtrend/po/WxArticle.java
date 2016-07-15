package com.fisher.wxtrend.po;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2016/7/14/.
 */
public class WxArticle {

    /**
     * contentImg : http://app1.showapi.com/weixin_info/article/0df9ffd5-c70c-4c35-bf1a-c16b7de5acdb.jpg
     * date : 2015-08-13 10:53
     * id : 55cc27086e36a9c5946e9004
     * title : [影像]史上最霸气的4张照片，一定要横着看!
     * typeId : 0
     * typeName : 热点
     * url : http://mp.weixin.qq.com/s?__biz=MjM5MjAxNDM4MA==&mid=221359497&idx=4&sn=912db380b6df66405eacd81861e12e92&3rd=MzA3MDU4NTYzMw==&scene=6#rd
     * userLogo : http://app1.showapi.com/weixin_info/article/89d3162e-a9af-4621-a31f-efb94d5ac99c.jpg
     * userLogo_code : http://app1.showapi.com/weixin_info/article/1240f53a-acab-4406-9724-cdf6b8ab53b8.jpg
     * userName : 人民日报
     */

    @SerializedName("contentImg")
    private String contentImg;
    @SerializedName("date")
    private String date;
    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("typeId")
    private String typeId;
    @SerializedName("typeName")
    private String typeName;
    @SerializedName("url")
    private String url;
    @SerializedName("userLogo")
    private String userLogo;
    @SerializedName("userLogo_code")
    private String userLogo_code;
    @SerializedName("userName")
    private String userName;

    public String getContentImg() { return contentImg;}

    public void setContentImg(String contentImg) { this.contentImg = contentImg;}

    public String getDate() { return date;}

    public void setDate(String date) { this.date = date;}

    public String getId() { return id;}

    public void setId(String id) { this.id = id;}

    public String getTitle() {
        if (!TextUtils.isEmpty(title)) {
            title = title.replaceAll("&nbsp;", " ");
        }
        return title;
    }

    public void setTitle(String title) { this.title = title;}

    public String getTypeId() { return typeId;}

    public void setTypeId(String typeId) { this.typeId = typeId;}

    public String getTypeName() { return typeName;}

    public void setTypeName(String typeName) { this.typeName = typeName;}

    public String getUrl() { return url;}

    public void setUrl(String url) { this.url = url;}

    public String getUserLogo() { return userLogo;}

    public void setUserLogo(String userLogo) { this.userLogo = userLogo;}

    public String getUserLogo_code() { return userLogo_code;}

    public void setUserLogo_code(String userLogo_code) { this.userLogo_code = userLogo_code;}

    public String getUserName() { return userName;}

    public void setUserName(String userName) { this.userName = userName;}
}
