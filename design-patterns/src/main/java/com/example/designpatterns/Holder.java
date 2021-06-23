package com.example.designpatterns;

/**
 * 静态内部类实现
 *
 * @author liuyzh
 * @date 2020/7/19
 */
public class Holder {

    private Holder() {
        System.out.println(Thread.currentThread().getName() + " ok.");
    }

    public static Holder getInstance() {
        // 会返回多个实例
//        return new Holder();
        // 只返回一个实例
        return InnerClass.HOLDER;
    }

    private static class InnerClass {
        // 不会在外部类初始化时就直接加载，只有当调用了getInstance方法时才会静态加载，线程安全。
        private static final Holder HOLDER = new Holder();
    }

    public static void main(String[] args) {
        // 多线程并发
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                getInstance();
            }).start();
        }
    }

}
