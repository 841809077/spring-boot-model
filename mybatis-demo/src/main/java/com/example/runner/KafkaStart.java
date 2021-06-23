package com.example.runner;

import com.example.utils.kafka.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author liuyzh
 * @date 2020/11/18
 */
//@Component
public class KafkaStart implements CommandLineRunner {

    @Autowired
    private Consumer consumer;

    @Value("${kafka.partitionCount:1}")
    private Integer partitionCount;

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < partitionCount; i++) {
            consumer.ceshi();
        }
    }
}
