package com.example.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Liuyongzhi
 * @description
 * @date 2020/2/16 0016
 */
@Data
public class Staff {

    private String personName;
    private String person_id;
    private String personnation;
    private Integer AGE;
    private String sex;
    private Integer telPhone;
    private Date birthday;
}
