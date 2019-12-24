package com.example.enums;

import com.example.exception.CustomException;

/**
 * @author Liuyongzhi
 * @description:
 * @date 2019/12/24 0024
 */
public enum ResultEnum {

    SUCCESS("1", "请求成功"),
    ERROR("-1", "未知错误"),
    HTTP_ERROR("-3", "请求错误"),
    RUNTIME_ERROR("-2", "运行时异常");

    private String code;
    private String msg;

    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
