//package com.example.utils.cm;
//
//import com.alibaba.fastjson.JSONObject;
//import com.cloudera.api.ApiRootResource;
//import com.cloudera.api.ClouderaManagerClientBuilder;
//import com.cloudera.api.model.ApiTimeSeriesRequest;
//
///**
// * @author liuyzh
// * @date 2020/8/4
// */
//public class ClouderaManagerCluster {
//
//    private static String CDH_API_HOST = "114.115.146.124";
//    private static Integer CDH_API_PORT = 7180;
//    private static String CDH_API_USERNAME = "admin";
//    private static String CDH_API_PASSWORD = "admin";
//    private static String DISK_QUERY = "SELECT total_capacity_across_filesystems WHERE category = \"HOST\"";
//
//    public static void getConnection() {
//        ApiRootResource root = new ClouderaManagerClientBuilder()
//                .withHost(CDH_API_HOST).withPort(CDH_API_PORT)
//                .withUsernamePassword(CDH_API_USERNAME, CDH_API_PASSWORD)
//                .build();
//
//        // 默认查询最近5分钟的值
//        ApiTimeSeriesRequest atsr = new ApiTimeSeriesRequest();
//        atsr.setQuery(DISK_QUERY);
//        atsr.setDesiredRollup("RAW");
//        atsr.setMustUseDesiredRollup(false);
//
//        Response res = root.getRootV30().getTimeSeriesResource().queryTimeSeries(atsr);
//
//        String jsonResponse = res.getEntity().toString();
//        JSONObject obj = JSONObject.parseObject(jsonResponse);
//        log.info("查询返回结果：{}", obj);
//    }
//
//}
