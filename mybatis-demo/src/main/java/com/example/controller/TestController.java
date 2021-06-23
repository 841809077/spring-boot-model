package com.example.controller;

import com.example.enums.ResultEnum;
import com.example.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 接口层
 *
 * @author Liuyongzhi
 * @date 2019/12/24 0024
 */
@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping(value = "/noError")
    public Object noError() {
        String data = "ok,you are right";
        return Result.ok(data);
    }

    @GetMapping(value = "/hasError")
    public Object hasError() {
        String a = "".split(",")[1];
        // a 会报数组越界异常
        return Result.ok(a);
    }

    @GetMapping(value = "/customError")
    public Result<String> customError() {
//        对于业务异常我们可以抛出自定义异常
        if (true) {
            return Result.failed(ResultEnum.ERROR);
        }

        // 对于一些验证规则，我们可以根据验证结果选择使用resultUtil.success() 或者 resultUtil.error()

        return Result.ok("");
    }
}
