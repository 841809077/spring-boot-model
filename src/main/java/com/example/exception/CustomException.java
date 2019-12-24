package com.example.exception;

import com.example.enums.ResultEnum;

/**
 * @author Liuyongzhi
 * @description:
 * @date 2019/12/24 0024
 */
public class CustomException extends RuntimeException {

    private String code;

    private String msg;

    public CustomException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
//        this.msg = resultEnum.getMsg();
    }
}
