package com.example.controller;

import com.example.enums.ResultEnum;
import com.example.exception.CustomException;
import com.example.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Liuyongzhi
 * @description
 * @date 2019/12/24 0024
 */
@RestController
@RequestMapping("test")
public class testController {

    @Autowired
    private ResultUtil resultUtil;

    @GetMapping(value = "/noError")
    public Object noError() {
        String data = "ok,you are right";
        return resultUtil.success(data);
    }

    @GetMapping(value = "/hasError")
    public Object hasError() {
        String a = "".split(",")[1];
        // a 会报数组越界异常
        return resultUtil.success(a);
    }

    @GetMapping(value = "/customError")
    public Object customError() {
//        对于业务异常我们可以抛出自定义异常
        if (true) {
            throw new CustomException(ResultEnum.ERROR);
        }

        return resultUtil.success("");
    }
}
