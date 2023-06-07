package com.scut.backend.utils;

public enum ResultEnum {

    /**
     * SUCCESS:成功
     * ERROR：失败
     */
    ERROR(0, "操作失败"),
    SUCCESS(200, "操作成功！"),
    UNAUTHORIZED(401, "未授权"),
    AUTHERROR(403, "授权验证失败"),

    //---系统错误返回码-----
    UNKNOWN_ERROR(-1, "未知错误"),
    FAIL(10001, "系统失败"),
    UNAUTHENTICATED(10002, "您还未登录"),
    UNAUTHORISE(10003, "权限不足"),
    SERVER_ERROR(99999, "抱歉，系统繁忙，请稍后重试！");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
