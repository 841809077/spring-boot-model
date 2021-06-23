package com.example.exception;

import com.example.enums.ResultEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Liuyongzhi
 * @description:
 * @date 2019/12/24 0024
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomException extends RuntimeException {

    private Integer code;

    private String msg;

    public CustomException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getMsg();
    }

    public CustomException(int code, String msg) {
        // 自定义错误栈中显示的message
        super(msg);
        this.code = code;
        this.msg = msg;
    }
}
