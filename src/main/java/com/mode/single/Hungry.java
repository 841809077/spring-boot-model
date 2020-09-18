package com.mode.single;

/**
 * 饿汉式单例：有可能浪费内存
 *
 * @author liuyzh
 * @date 2020/7/19
 */
public class Hungry {

    // 有可能会浪费内存
    private byte[] data1 = new byte[1024 * 1024];
    private byte[] data2 = new byte[1024 * 1024];
    private byte[] data3 = new byte[1024 * 1024];
    private byte[] data4 = new byte[1024 * 1024];

    private Hungry() {

    }

    private static Hungry HUNGRY = new Hungry();

    public static Hungry getInstance() {
        return HUNGRY;
    }

}
