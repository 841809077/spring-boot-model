package com.example.spring_thread_pool;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Spring的ThreadPoolTaskExecutor线程池
 *
 * @author liuyongzhi
 * @date 2021/06/24 16:37:24
 */
public class ThreadPoolTaskExecutorDemo {

    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolTaskExecutorDemo.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        logger.info("当前线程总数为：{}", bean.getThreadCount());
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("thread-");
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(1);
        executor.setKeepAliveSeconds(2);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        executor.initialize();

        List<Future<?>> futureList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Future<?> aa = executor.submit(new Callable<Object>() {
                @SneakyThrows
                @Override
                public Object call() {
                    TimeUnit.SECONDS.sleep(3);
                    logger.info("{}", Thread.currentThread().isAlive());
                    return "11111";
                }
            });
            futureList.add(aa);
        }
        for(Future<?> f: futureList){
            logger.info("{}, {}", f.isDone(), f.get());
        }
        TimeUnit.SECONDS.sleep(5);
        logger.info("当前线程总数为：{}", bean.getThreadCount());
        // 如果不执行线程池关闭操作，则最后留存的线程数量只有使用过的核心线程数
//        executor.shutdown();
        executor.shutdown();
        TimeUnit.SECONDS.sleep(10);
        logger.info("当前线程总数为：{}", bean.getThreadCount());



    }

}
