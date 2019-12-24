package com.example.utils;

import com.example.entity.Result;
import com.example.enums.ResultEnum;
import org.springframework.stereotype.Component;

/**
 * @author Liuyongzhi
 * @description 返回结果工具类
 * @date 2019/12/24
 */
@Component
public class ResultUtil {

    /**
     * 固定成功提示，返回信息
     *
     * @param object
     * @return com.example.entity.Result<java.lang.Object>
     */
    public Result<Object> success(Object object) {
        Result<Object> result = new Result<>();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }

    /**
     * 固定失败提示，返回信息
     *
     * @return com.example.entity.Result<java.lang.Object>
     */
    public Result<Object> error() {
        Result<Object> result = new Result<>();
        result.setCode(ResultEnum.ERROR.getCode());
        result.setMsg(ResultEnum.ERROR.getMsg());
        result.setData(null);
        return result;
    }

    /**
     * 自定义失败提示，返回信息
     *
     * @return com.example.entity.Result<java.lang.Object>
     */
    public Result<Object> error(String code, String msg) {
        Result<Object> result = new Result<>();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    /**
     * 枚举失败提示，返回信息
     *
     * @param resultEnum
     * @return com.example.entity.Result<java.lang.Object>
     */
    public Result<Object> error(ResultEnum resultEnum) {
        Result<Object> result = new Result<>();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        result.setData(null);
        return result;
    }

}
