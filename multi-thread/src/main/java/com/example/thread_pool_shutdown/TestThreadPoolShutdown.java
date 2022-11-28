package com.example.thread_pool_shutdown;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TestThreadPoolShutdown {

    private static final Logger logger = LoggerFactory.getLogger(TestThreadPoolShutdown.class);

    private static ExecutorService executorService = Executors.newFixedThreadPool(4);

    private static List<Future<String>> futureList = new ArrayList<>();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future<String> t1 = executorService.submit(new Task());
        Future<String> t2 = executorService.submit(new Task());
        Future<String> t3 = executorService.submit(new TimeLongTask());
        Future<String> t4 = executorService.submit(new Task());
        futureList.add(t1);
        futureList.add(t2);
        futureList.add(t3);
        futureList.add(t4);

        executorService.shutdown();

//        for (Future<String> f : futureList) {
//            logger.info("{}", f.get());
//            logger.info("{}", f.isDone());
//            while(!f.isDone()){
//                logger.info("线程{}还在工作", Thread.currentThread().getName());
//            }
//        }

        while (!executorService.awaitTermination(1, TimeUnit.SECONDS)){
            logger.info("线程{}还在工作", Thread.currentThread().getName());
        }

        logger.info("线程池已经关闭");

    }
}
