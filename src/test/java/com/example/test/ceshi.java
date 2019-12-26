package com.example.test;

import com.example.entity.NovelType;
import com.example.entity.UserInfo;
import com.example.service.NovelTypeService;
import com.example.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author Liuyongzhi
 * @description
 * @date 2019/12/25 0025
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ceshi {

    @Autowired
    private NovelTypeService novelTypeService;

    @Autowired
    private UserInfoService userInfoService;

    @Test
    public void aa() {
        log.info("--------");
    }

    /**
     * 多数据源测试
     */
    @Test
    public void dynamicSource() {
        List<NovelType> novelTypes = novelTypeService.list();
        log.info("db1 的 node_type 表数据为：{}", novelTypes);
        List<UserInfo> userInfos = userInfoService.list();
        log.info("db2 的 userinfo 表数据为：{}", userInfos);
    }

}
