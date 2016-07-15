package com.fisher.wxtrend.po;

import com.google.gson.annotations.SerializedName;

public class ApiResult<T> {


    @SerializedName("showapi_res_code")
    private String code;

    @SerializedName("showapi_res_error")
    private String error;

    @SerializedName("showapi_res_body")
    private T body;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }
}