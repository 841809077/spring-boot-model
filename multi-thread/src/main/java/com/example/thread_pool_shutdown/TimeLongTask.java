package com.example.thread_pool_shutdown;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 线程耗时长的任务
 *
 * @author liuyongzhi
 * @date 2021/06/29 11:14:31
 */
public class TimeLongTask implements Callable<String> {
    @Override
    public String call() throws Exception {
        TimeUnit.SECONDS.sleep(10);
        return "这是一个耗时长的任务";
    }
}
