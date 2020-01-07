package com.example.utils;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

/**
 * @author liuyzh
 * @description http 请求，并获取返回信息
 * 参考链接：https://www.hutool.cn/docs/#/http/Http%E8%AF%B7%E6%B1%82-HttpRequest
 * @date 2020-01-07
 */
public class RepUtil {

    /**
     * get 请求
     *
     * @param url
     * @return
     */
    public static JSONObject get(String url) {
        String response = HttpRequest.get(url).execute().body();
        return JSONUtil.parseObj(response);
    }

    /**
     * post 请求
     *
     * @param url
     * @param reqBody
     * @return
     */
    public static JSONObject post(String url, String reqBody) {
        String response = HttpRequest.post(url).body(reqBody).execute().body();
        return JSONUtil.parseObj(response);
    }

    /**
     * put 请求
     *
     * @param url
     * @return
     */
    public static JSONObject put(String url, String reqBody) {
        String response = HttpRequest.put(url).body(reqBody).execute().body();
        return JSONUtil.parseObj(response);
    }

    /**
     * delete 请求
     *
     * @param url
     * @return
     */
    public static JSONObject delete(String url, String reqBody) {
        String response = HttpRequest.delete(url).body(reqBody).execute().body();
        return JSONUtil.parseObj(response);
    }


}
