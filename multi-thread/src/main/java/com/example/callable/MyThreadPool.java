package com.example.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 基于线程池创建有返回值的线程，与{@link MyCallable}联用
 *
 * @author liuyongzhi
 * @date 2021/06/22 16:53:59
 */
public class MyThreadPool {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int taskSize = 3;
        //创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(taskSize);
        // 创建多个有返回值的任务
        List<Future<String>> list = new ArrayList<>();
        for (int i = 0; i < taskSize; i++) {
            Callable<String> c = new MyCallable(i);
            // 执行任务并获取 Future 对象
            Future<String> f = pool.submit(c);
            list.add(f);
        }
        // 关闭线程池
        pool.shutdown();
        // 获取所有并发任务的运行结果
        for (Future<String> f : list) {
            // 从 Future 对象上获取任务的返回值，并输出到控制台
            System.out.println("res：" + f.get().toString());
        }
    }

}
