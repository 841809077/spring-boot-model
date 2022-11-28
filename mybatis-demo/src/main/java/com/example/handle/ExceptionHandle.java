package com.example.handle;

import com.example.enums.ResultEnum;
import com.example.exception.CustomException;
import com.example.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;

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
    public Result<Object> handle(HttpServletResponse response, Exception e) {
        log.error("出现异常：", e);
        if (e instanceof CustomException) {
            //如果是我们自定义的异常，就直接返回我们异常里面设置的信息
            CustomException customException = (CustomException) e;
            return Result.failed(customException.hashCode(), customException.getMessage());
        } else if (e instanceof ValidationException) {
            return Result.failed(1003, e.getMessage());
        } else if (e instanceof BindException) {
            return Result.failed(1003, ((BindException) e).getBindingResult().getAllErrors().get(0).getDefaultMessage());
        } else if (e instanceof HttpClientErrorException) {
            return Result.failed(e.getMessage());
        } else if (e instanceof HttpMessageNotReadableException) {
            response.setStatus(400);
            return Result.failed(ResultEnum.HTTP_MESSAGE_NOT_READABLE);
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            response.setStatus(405);
            return Result.failed(ResultEnum.HTTP_STATUS_METHOD_NOT_ALLOWED.getCode(), e.getMessage());
        } else {
            response.setStatus(500);
            return Result.failed(ResultEnum.HTTP_STATUS_INTERNAL_SERVER_ERROR);
        }
    }

}
