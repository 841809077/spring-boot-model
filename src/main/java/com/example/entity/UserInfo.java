package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author liuyzh
 * @description
 * @date 2019-12-26
 */
@Data
@TableName("userinfo")
public class UserInfo {

    @TableId
    private Integer id;

    @TableField("person_id")
    private String personId;

    @TableField("department_id")
    private String departmentId;

}
