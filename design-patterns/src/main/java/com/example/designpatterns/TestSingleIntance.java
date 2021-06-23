package com.example.designpatterns;

import java.lang.reflect.Constructor;

/**
 * @author liuyzh
 * @date 2020/7/19
 */
public class TestSingleIntance {

    public static void main(String[] args) throws Exception {
//        LazyMan instance = LazyMan.getInstance();
//        LazyMan instance1 = LazyMan.getInstance();
//        System.out.println(instance == instance1); //true
//
//        Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor();
//        //关闭权限检测
//        declaredConstructor.setAccessible(true);
//        LazyMan instance2 = declaredConstructor.newInstance();
//        System.out.println(instance == instance2); //false，表示利用反射可以创建多个对象

        Holder instance = Holder.getInstance();
        Holder instance1 = Holder.getInstance();
        System.out.println(instance == instance1); //true

        Constructor<Holder> declaredConstructor = Holder.class.getDeclaredConstructor();
        //关闭权限检测
        declaredConstructor.setAccessible(true);
        Holder instance2 = declaredConstructor.newInstance();
        System.out.println(instance == instance2); //false，表示利用反射可以创建多个对象
    }

}
