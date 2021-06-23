package com.example;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSON;
import com.example.entity.NovelType;
import com.example.entity.Staff;
import com.example.service.NovelTypeService;
import com.example.service.UserInfoService;
import com.example.thread.ThreadTest;
import com.example.utils.RepUtil;
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
 * 单元测试类
 *
 * @author Liuyongzhi
 * @date 2021/06/23 17:22:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Ceshi {

    @Autowired
    private NovelTypeService novelTypeService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private ThreadTest threadTest;

//    private static Logger log = LoggerFactory.getLogger(ceshi.class);

    /**
     * 多线程测试
     */
    @Test
    public void threadTest() {
        log.info("--------");
        threadTest.ceshi();
        threadTest.ceshi2();
    }

    @Test
    public void getImgByCardId() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sfzh", "379008194711040270");
        JSONObject resJsonObj = RepUtil.post("http://192.168.166.189:5101/face/query", JSON.toJSONString(jsonObject));
//        log.info(resJsonObj.toString());
        System.out.println(resJsonObj.toString());
    }

    /**
     * 多数据源测试
     */
    @Test
    public void dynamicSource() {
        List<NovelType> novelTypes = novelTypeService.list();
        log.info("db1 的 node_type 表数据为：{}", novelTypes);
        log.error("db1 的 node_type 表数据为：{}", novelTypes);
//        List<UserInfo> userInfos = userInfoService.list();
//        log.info("db2 的 userinfo 表数据为：{}", userInfos);
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
//        UserInfo userInfo = new UserInfo();
//        userInfo.setPersonId("358255..");
//        userInfoService.save(userInfo);
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

    @Test
    public void aac(){
//        String jsonStr = "{\"DispositionNotificationListObject\":{\"DispositionNotificationObject\":[{\"PersonObject\":{\"GenderCode\":0,\"EthicCode\":0,\"DeviceID\":\"0\",\"SourceID\":\"022019121117052900016\",\"SubImageList\":{\"SubImageInfoObject\":[{\"Type\":\"11\",\"StoragePath\":\"http://192.168.166.203:11180/storage/v1/image/global?cluster_id=ShenSi&uri_base64=bm9ybWFsOi8vcmVwb3NpdG9yeS1idWlsZGVyLzIwMjAwMTIwL1ZRQnV1LVlsWFpGd29lN1dJSnlXUmc9PUAx\",\"DeviceID\":\"0\",\"ImageID\":\"022019121117052900016\",\"EventSort\":0,\"ShotTime\":\"19700119134716\",\"Height\":-1,\"FileFormat\":\"jpg\",\"Width\":-1},{\"Type\":\"11\",\"StoragePath\":\"http://192.168.166.203:11180/storage/v1/image/global?cluster_id=ShenSi&uri_base64=bm9ybWFsOi8vcmVwb3NpdG9yeS1idWlsZGVyLzIwMjAwMTIwL0gzc1dYN3lXRmh1Zmd0Sjd6Tlo1cnc9PUAx\",\"DeviceID\":\"0\",\"ImageID\":\"022019121117052900016\",\"EventSort\":0,\"ShotTime\":\"19700119134600\",\"Height\":-1,\"FileFormat\":\"jpg\",\"Width\":-1},{\"Type\":\"14\",\"StoragePath\":\"http://192.168.166.203:11180/storage/v1/image/global?cluster_id=ShenSi&uri_base64=bm9ybWFsOi8vcmVwb3NpdG9yeS1idWlsZGVyLzIwMjAwMTIwL0JXS3RIQm1aVXpUT3prNzJOYW50S1E9PUAx\",\"DeviceID\":\"0\",\"ImageID\":\"022019121117052900016\",\"EventSort\":0,\"ShotTime\":\"19700119134600\",\"Height\":-1,\"FileFormat\":\"jpg\",\"Width\":-1}]},\"LeftTopY\":141,\"LeftTopX\":104,\"Name\":\"图片姓名测试\",\"InfoKind\":1,\"PersonID\":\"0220191211170529000180100019\",\"RightBtmY\":806,\"RightBtmX\":932,\"IDNumber\":\"\"},\"NotificationID\":\"202002031736\",\"DispositionID\":\"71\",\"TriggerTime\":\"2020-02-03 15:34:15\",\"Title\":\"第三方1400告警信息接收测试\"}]}}";
//        JSONObject json = new JSONObject(jsonStr);
//        JSONObject dispositionNotificationListObject = json.getJSONObject("DispositionNotificationListObject");
//        System.out.println(dispositionNotificationListObject);

        String jsonArrayStr = "[{\"Type\":\"11\",\"StoragePath\":\"http://192.168.166.203:11180/storage/v1/image/global?cluster_id=ShenSi&uri_base64=bm9ybWFsOi8vcmVwb3NpdG9yeS1idWlsZGVyLzIwMjAwMTIwL1ZRQnV1LVlsWFpGd29lN1dJSnlXUmc9PUAx\",\"DeviceID\":\"0\",\"ImageID\":\"022019121117052900016\",\"EventSort\":0,\"ShotTime\":\"19700119134716\",\"Height\":-1,\"FileFormat\":\"jpg\",\"Width\":-1},{\"Type\":\"11\",\"StoragePath\":\"http://192.168.166.203:11180/storage/v1/image/global?cluster_id=ShenSi&uri_base64=bm9ybWFsOi8vcmVwb3NpdG9yeS1idWlsZGVyLzIwMjAwMTIwL0gzc1dYN3lXRmh1Zmd0Sjd6Tlo1cnc9PUAx\",\"DeviceID\":\"0\",\"ImageID\":\"022019121117052900016\",\"EventSort\":0,\"ShotTime\":\"19700119134600\",\"Height\":-1,\"FileFormat\":\"jpg\",\"Width\":-1},{\"Type\":\"14\",\"StoragePath\":\"http://192.168.166.203:11180/storage/v1/image/global?cluster_id=ShenSi&uri_base64=bm9ybWFsOi8vcmVwb3NpdG9yeS1idWlsZGVyLzIwMjAwMTIwL0JXS3RIQm1aVXpUT3prNzJOYW50S1E9PUAx\",\"DeviceID\":\"0\",\"ImageID\":\"022019121117052900016\",\"EventSort\":0,\"ShotTime\":\"19700119134600\",\"Height\":-1,\"FileFormat\":\"jpg\",\"Width\":-1}]";
        JSONArray jsonArray = new JSONArray(jsonArrayStr);
        for(int j=0; j< jsonArray.size(); j++){
            JSONObject subImageInfoObject = jsonArray.getJSONObject(j);
            String Type = subImageInfoObject.getStr("Type");
            System.out.println(Type);
        }
    }

    /**
     * json字符串 与 对象的互相转化
     */
    @Test
    public void jsonToBeanObject(){

        // bean : personName, person_id, personnation, AGE, sex, telPhone, birthday(Date)
        // json : personname, person_id, personNation, aGe, SEX, telPhone, birthday

        // json字符串转化为对象
        String jsonString = "{personname:'Antony','person_id':'371xxxxxxxxxxx','personNation':'汉族','aGe':'13','SEX':'male','telPhone':'88888','birthday': '2020-02-14 00:00:00'}";
        Staff staff = JSON.parseObject(jsonString, Staff.class);
        log.info(staff.toString());

        // 对象转化为json字符串
        String jsonStr = JSON.toJSONString(staff);
        log.info(jsonStr);
    }

}
