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
@TableName("novel_type")
public class NovelType {

    @TableId
    private Long id;

    @TableField("novelname")
    private String novelName;

    @TableField("novelauthor")
    private String novelAuthor;

    @TableField("type")
    private String type;

    @TableField("introduce")
    private String introduce;

    @TableField("download")
    private String download;

}
