package com.example.test;

import com.example.entity.NovelType;
import com.example.entity.UserInfo;
import com.example.service.NovelTypeService;
import com.example.service.UserInfoService;
import com.example.thread.ThreadTest;
import com.example.utils.UrlImg;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private ThreadTest threadTest;

    /**
     * 多线程测试
     */
    @Test
    public void threadTest() {
        log.info("--------");
        threadTest.ceshi();
        threadTest.ceshi2();
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

    @Test
    public void transaction() {
        save();
    }

    @Transactional(rollbackFor = Exception.class)
    public void save() {
        NovelType novelType = new NovelType();
        novelType.setIntroduce("小说畅销榜单之一");
        novelTypeService.save(novelType);
        UserInfo userInfo = new UserInfo();
        userInfo.setPersonId("358255..");
        userInfoService.save(userInfo);
    }

    /**
     * 图片url转base64测试
     */
    @Test
    public void imgUrlToBase64Test() {
        String base64 = UrlImg.loadImg("https://upload-images.jianshu.io/upload_images/5225109-d5a7a2a49801d829.png?imageMogr2/auto-orient/strip|imageView2/2/w/1200/format/webp");
        log.info(base64);
        log.info("------");
        System.out.println(base64);
    }

}
