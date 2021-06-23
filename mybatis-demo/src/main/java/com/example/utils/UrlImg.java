package com.example.utils;

import com.alibaba.druid.util.Base64;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * @author liuyzh
 * @description 图片工具类
 * 参考链接：
 * @date 2020-01-07
 */
@Slf4j
public class UrlImg {

    /**
     * 通过url读取图片，并返回base64
     *
     * @param imgurl 图片地址。
     * @return
     */
    public static String loadImg(String imgurl) {
        ByteArrayOutputStream outPut = null;
        InputStream inStream = null;
        byte[] data = new byte[1024];
        HttpURLConnection conn = null;
        try {
            // 创建URL
            URL url = new URL(imgurl);
            // 创建链接
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10 * 1000);
            inStream = conn.getInputStream();
            outPut = new ByteArrayOutputStream();
            int len = -1;
            while ((len = inStream.read(data)) != -1) {
                outPut.write(data, 0, len);
            }
        } catch (IOException e) {
            log.error(">>> 下载图片失败！ <<<" + imgurl, e);
        } finally {
            try {
                if (null != inStream) {
                    inStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String base64 = "";
        try {
            if (null != outPut && outPut.size() > 0) {
                base64 = Base64.byteArrayToBase64(outPut.toByteArray());
            }
        } catch (Exception e) {
            log.error("图片下载转换为base64失败！");
        }

        return base64;
    }

    /**
     * 下载图片为输入流
     *
     * @param zpImgIp
     * @param imgurl
     * @return
     */
    public static InputStream loadImgToInputStream(String zpImgIp, String imgurl) {
        InputStream inStream = null;
        HttpURLConnection conn = null;
        try {
            // 创建URL
            String urlString = URLEncoder.encode(imgurl, "UTF-8").replace("+", "%20");
            URL url = new URL(zpImgIp + urlString);
            // 创建链接
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(10 * 1000);
            inStream = conn.getInputStream();
        } catch (IOException e) {
            log.error(">>>" + imgurl + " 下载图片失败-inputStream！ <<<", e);
        }
        return inStream;
    }

    /**
     * out转化为base64
     *
     * @param outPut
     * @return
     */
    public static String outPutStreamToBase64(ByteArrayOutputStream outPut) {
        String base64 = "";
        try {
            if (null != outPut && outPut.size() > 0) {
                base64 = Base64.byteArrayToBase64(outPut.toByteArray());
            }
        } catch (Exception e) {
            log.error("输出流转换为图片base64失败！");
        }
        return base64;
    }

    /**
     * 输入流转输出流
     *
     * @param in
     * @return
     * @throws Exception
     */
    public static ByteArrayOutputStream parse(InputStream in) throws Exception {
        ByteArrayOutputStream outPut = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len = -1;
        while ((len = in.read(data)) != -1) {
            outPut.write(data, 0, len);
        }
        return outPut;
    }

    /**
     * 通过图片base64流判断图片等于多少字节
     * image 图片流
     */
    public static Integer imageSize(String base64) {
        // 1.找到等号，把等号也去掉
        int equalIndex = base64.indexOf("=");
        if (base64.indexOf("=") > 0) {
            base64 = base64.substring(0, equalIndex);
        }
        // 2.原来的字符流大小，单位为字节
        int strLength = base64.length();
        // 3.计算后得到的文件流大小，单位为字节
        return strLength - (strLength / 8) * 2;
    }

    /**
     * 关闭输入流
     *
     * @param in
     */
    public static void closeInputStream(InputStream in) {
        try {
            if (null != in) {
                in.close();
            }
        } catch (IOException e) {
            log.error("InputStream 关闭异常：", e);
        }
    }
}
