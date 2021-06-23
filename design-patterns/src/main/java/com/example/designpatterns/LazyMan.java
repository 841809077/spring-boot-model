package com.example.designpatterns;

/**
 * 懒汉式单例
 * DCL懒汉式(双重检测锁模式)
 * 参考资料：https://blog.csdn.net/baolingye/article/details/101106783
 *
 * @author liuyzh
 * @date 2020/7/19
 */
public class LazyMan {

    private LazyMan() {
        System.out.println(Thread.currentThread().getName() + "ok.");
    }

    // 只提供一个实例，并不创建对象
    // 使用volatile关键字，来避免指令重排带来的线程安全问题
    private volatile static LazyMan lazyMan;

    public static LazyMan getInstance() {
        // 双重检测锁模式的 懒汉式单例， DCL懒汉式
        if (lazyMan == null) {
            synchronized (LazyMan.class) {
                if (lazyMan == null) {
                    lazyMan = new LazyMan();  // 不是一个原子性操作
                    /**
                     * new 对象的步骤：
                     * 1、分配内存空间
                     * 2、执行构造方法，初始化对象
                     * 3、把这个对象指向这个空间
                     *
                     * 有时会发生指令重排的操作
                     *  1、有可能是执行步骤123
                     *  2、也有可能是步骤132
                     *  比如A线程执行了步骤132，这时候又加了一个B线程，
                     *  B此时认为lazyMan有了对象，不等于null。但实际上对象还没有完成构造。
                     *
                     *  为避免指令重排，需要加volatile关键字。
                     */
                }
            }
        }
        return lazyMan;
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
