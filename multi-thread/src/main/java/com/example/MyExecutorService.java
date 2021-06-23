package com.example;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池 execute
 * 类型：
 * newFixedThreadPool
 * newCachedThreadPool
 * newScheduledThreadPool
 * newSingleThreadExecutor
 *
 * 线程池不建议使用Executors去创建，虽然提供了创建线程池的方法，但都有局限性，不够灵活，比如不能设置拒绝策略等
 * 建议使用new ThreadPoolExecutor()方式
 *
 * @author liuyongzhi
 * @date 2021/06/22 17:37:40
 */
public class MyExecutorService {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " is running ..");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        while(true) {
            threadPool.execute(new Runnable() { // 提交多个线程任务，并执行
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " is running ..");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
