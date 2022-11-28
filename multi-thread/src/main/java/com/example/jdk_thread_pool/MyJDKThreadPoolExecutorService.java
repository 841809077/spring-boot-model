package com.example.jdk_thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * 通过Executors去创建线程池，jdk自带
 *
 * 类型：
 * newFixedThreadPool: 固定核心线程的线程池，采用的阻塞队列是LinkedBlockingQueue,是一个无界队列，当任务量突然很大,线程池来不及处理，就会将任务一直添加到队列中,就有可能导致内存溢出
 * newSingleThreadExecutor: 单线程线程池，采用的阻塞队列是LinkedBlockingQueue,是一个无界队列，有可能导致内存溢出
 * newCachedThreadPool: 无核心线程，"无限大"的线程数，当任务量突然很大，会一直创建线程去处理，也有可能导致内存溢出
 * newScheduledThreadPool: 定长线程池，支持定时及周期性任务执行。"无限大"的线程数，有可能导致内存溢出
 *
 *
 * 提交方式：
 * execute(Runnable)
 * submit(Runnable)
 * submit(Callable)
 * invokeAny(Collection<Callable>)
 * invokeAll(Collection<Callable>)
 *
 * 线程池不建议使用Executors去创建，虽然提供了创建线程池的方法，但都有局限性，不够灵活，比如不能设置拒绝策略等
 * 建议使用new ThreadPoolExecutor()方式
 *
 * 线程池工作流程：
 * - 当一个任务被提交到线程池时，首先查看线程池的核心线程是否都在执行任务。如果没有，则选择一条核心线程执行任务。
 * - 如果核心线程都在执行任务，查看任务队列是否已满。如果不满，则将任务存储在任务队列中。核心线程执行完自己的任务后，会再处理任务队列中的任务。
 * - 如果任务队列已满，查看线程池（最大线程数控制）是否已满。如果不满，则创建一条线程去执行任务。如果满了，就按照策略处理无法执行的任务。
 *
 * @author liuyongzhi
 * @date 2021/06/22 17:37:40
 */
public class MyJDKThreadPoolExecutorService {

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(1);

        Executors.newSingleThreadExecutor();

        Executors.newCachedThreadPool();

        ScheduledExecutorService aa= Executors.newScheduledThreadPool(10);

        while(true) {
            threadPool.submit(new Runnable() { // 提交多个线程任务，并执行
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
