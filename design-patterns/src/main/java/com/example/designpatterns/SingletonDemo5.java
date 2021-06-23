package com.example.designpatterns;

import java.lang.reflect.Constructor;

/**
 * @author liuyzh
 * @date 2020/7/19
 */
public enum SingletonDemo5 {

    INSTANCE;

    public static SingletonDemo5 getInstance() {
        return INSTANCE;
    }
}

class SingletonDemo5Test {
    public static void main(String[] args) throws Exception {
        SingletonDemo5 instance = SingletonDemo5.getInstance();
        SingletonDemo5 instance1 = SingletonDemo5.getInstance();

        Constructor<SingletonDemo5> declaredConstructor = SingletonDemo5.class.getDeclaredConstructor(String.class, int.class);
        //关闭权限检测
        declaredConstructor.setAccessible(true);
        SingletonDemo5 instance2 = declaredConstructor.newInstance();

        System.out.println(instance == instance1); //true
    }
}
