package com.example.thread_pool_shutdown;

import java.util.concurrent.Callable;

/**
 * 线程普通任务
 *
 * @author liuyongzhi
 * @date 2021/06/29 11:13:03
 */
public class Task implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "这是一个普通任务";
    }
}
