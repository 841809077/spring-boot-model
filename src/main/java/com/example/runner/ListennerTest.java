package com.example.runner;

import com.example.thread.ThreadTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author liuyzh
 * @description
 * @date 2020-01-08
 */
@Component
@Order(1)
@Slf4j
public class ListennerTest implements CommandLineRunner {

    @Autowired
    private ThreadTest threadTest;

    @Override
    public void run(String... args) throws Exception {
        log.info("--------");
        threadTest.ceshi();
        threadTest.ceshi2();
    }
}
