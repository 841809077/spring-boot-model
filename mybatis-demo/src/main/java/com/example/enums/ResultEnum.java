package com.example.enums;

/**
 * @author Liuyongzhi
 * @description:
 * @date 2019/12/24 0024
 */
public enum ResultEnum {

    SUCCESS(1, "请求成功"),
    ERROR(-1, "未知错误"),
    HTTP_ERROR(-3, "请求错误"),
    RUNTIME_ERROR(-2, "运行时异常"),
    HTTP_MESSAGE_NOT_READABLE(1014, "Http message not readable, JSON parse error."),
    HTTP_STATUS_METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    HTTP_STATUS_INTERNAL_SERVER_ERROR(500, "Server Internal Error"),
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
