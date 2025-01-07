package com.pipi.security.constant;


public enum CustomHttpStatus {

    ERROR_LOGIN(31501, "未检测到登录用户_前端需跳转到登录界面");     // 前端收到此编码的消息时, 自动跳转到登录界面

    private final Integer STATUS_CODE;

    private final String MESSAGE;

    CustomHttpStatus(Integer statusCode, String message) {
        this.STATUS_CODE = statusCode;
        this.MESSAGE = message;
    }

    public Integer getStatusCode() {
        return STATUS_CODE;
    }

    public String getMessage() {
        return MESSAGE;
    }
}
