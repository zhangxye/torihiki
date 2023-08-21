package com.tsrs.webedi.common.constant.state;

/**
 * 日志类型
 *
 * @author Tsrs-WebEDI
 * @Date 2017年1月22日 下午12:14:59
 */
public enum LogType {

    LOGIN("ログイン成功"),
    LOGIN_FAIL("ログイン失敗"),
    EXIT("ログアウト"),
    EXCEPTION("異常ログ"),
    BUSSINESS("業務ログ");

    String message;

    LogType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
