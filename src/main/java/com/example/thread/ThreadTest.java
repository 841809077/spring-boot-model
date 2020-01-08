package com.example.thread;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author liuyzh
 * @description 多线程调用。ceshi() 和 ceshi2() 方法需要被调用
 * @date 2020-01-08
 */
@Component
@Slf4j
public class ThreadTest {

    @Async("asyncTaskExecutor")
    public void ceshi() {
        log.info("多线程测试 1111");
    }

    @Async("asyncTaskExecutor")
    public void ceshi2() {
        log.info("多线程测试 2222");
    }

}
