package com.example.entity;

import lombok.Data;

/**
 * @author Liuyongzhi
 * @description: 结果实体类
 * @date 2019/12/24
 */
@Data
public class Result<T> {

    private String code;
    private String msg;
    private T data;

}
