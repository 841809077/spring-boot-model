package com.example.handle;

import com.example.enums.ResultEnum;
import com.example.exception.CustomException;
import com.example.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Liuyongzhi
 * @description
 * @date 2019/12/24 0024
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandle {

    /**
     * 捕获异常 针对不同异常返回不同内容的固定格式信息
     * 拦截所有的异常，并且返回 json 格式的信息
     *
     * @param e 异常
     * @return result
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<Object> handle(Exception e) {
        log.error("出现异常：", e);
        if (e instanceof CustomException) {
            //如果是我们自定义的异常，就直接返回我们异常里面设置的信息
            CustomException customException = (CustomException) e;
            return Result.failed(customException.hashCode(), customException.getMessage());
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            //对其他的异常进行处理，如果是请求方法错误，我们设置 code 和 msg 进行返回。
            return Result.failed(ResultEnum.HTTP_ERROR);
        } else if (e instanceof RuntimeException) {
            return Result.failed(ResultEnum.RUNTIME_ERROR);
        } else {
            return Result.failed();
        }
    }

}
