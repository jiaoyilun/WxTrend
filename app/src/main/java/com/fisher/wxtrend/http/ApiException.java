package com.fisher.wxtrend.http;

public class ApiException extends RuntimeException {
/*
    public static final String MSG_API_PARAM_ERROR = "0001";
    public static final String MSG_API_UID_ERROR = "0002";
    public static final String MSG_API_USER_BANNED = "0003";
    public static final String MSG_API_KEY_INVALID = "0004";
    public static final String MSG_API_CODE_INVALID = "0005";
    public static final String MSG_API_REQUEST_OVER = "0006";
    public static final String MSG_API_SERVER_ERROR = "0007";*/


    public static final int MSG_API_PARAM_ERROR = 1;
    public static final int MSG_API_UID_ERROR = 2;
    public static final int MSG_API_USER_BANNED = 3;
    public static final int MSG_API_KEY_INVALID = 4;
    public static final int MSG_API_CODE_INVALID = 5;
    public static final int MSG_API_REQUEST_OVER = 6;
    public static final int MSG_API_SERVER_ERROR = 7;

    public ApiException(int errcode) {
        this(getApiExceptionMessage(errcode));
    }

    public ApiException(String detailMessage) {
        super(detailMessage);
    }


    /**
     * 由于服务器传递过来的错误信息直接给用户看的话，用户未必能够理解
     * 需要根据错误码对错误信息进行一个转换，在显示给用户
     * @param errcode
     * @return
     */
    private static String getApiExceptionMessage(int errcode){
        String message = "";
        switch (errcode) {
            case MSG_API_PARAM_ERROR:
                message = "传输参数格式有误";
                break;
            case MSG_API_UID_ERROR:
                message = "用户编号(uid)无效";
                break;
            case MSG_API_USER_BANNED:
                message = "用户被禁用";
                break;
            case MSG_API_KEY_INVALID:
                message = "key无效";
                break;
            case MSG_API_CODE_INVALID:
                message = "快递代号(id)无效";
                break;
            case MSG_API_REQUEST_OVER:
                message = "访问次数达到最大额度";
                break;
            case MSG_API_SERVER_ERROR:
                message = "查询服务器返回错误";
                break;
            default:
                message = "未知错误";

        }
        return message;
    }
}