package com.example.jdk_thread_pool;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * jdk的ThreadPoolExecutor：手动创建ThreadPoolExecutor线程池
 *
 * @author liuyongzhi
 * @date 2021/06/24 16:33:53
 */
public class ThreadPoolExecutorDemo {

    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolExecutorDemo.class);

    public static void main(String[] args) throws InterruptedException {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 60, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(6), new NamedThreadFactory("线程名称"), new ThreadPoolExecutor.AbortPolicy());

        System.out.println(threadPoolExecutor.getTaskCount());


//        try {
//            boolean timeout = threadPoolExecutor.awaitTermination(3, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            Thread.currentThread().interrupt();
//        }

        for (int i = 1; i <= 11; i++) {
            threadPoolExecutor.execute(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + "~~~~~~~~~~~~~");
                    TimeUnit.SECONDS.sleep(3);
                }
            });
        }
        System.out.println(threadPoolExecutor.getTaskCount());
        // 关闭线程池
        threadPoolExecutor.shutdown();
        while (!threadPoolExecutor.awaitTermination(1, TimeUnit.SECONDS)){
            logger.info("线程{}还在工作", Thread.currentThread().getName());
        }
        System.out.println(threadPoolExecutor.getTaskCount());
    }

}
