package com.example.utils.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @author liuyzh
 * @date 2020/11/18
 */
@Component
public class Consumer {

    private static final Logger log = LoggerFactory.getLogger(Consumer.class);

    @Value("${kafka.consumerInterval}")
    private String consumerInterval;

    @Value("${kafka.topic}")
    private String topic;

    @Async("asyncTaskExecutor")
    public void ceshi() {
        KafkaConsumer<String, String> consumer = KafkaUtil.getConsumer();
        log.info("{}", consumer);
        consumer.subscribe(Arrays.asList(topic));

        Iterator<ConsumerRecord<String, String>> recordIter;
        ConsumerRecords<String, String> records;

        while (true) {

            records = consumer.poll(Integer.parseInt(consumerInterval));
            if (records.count() < 1) {
                continue;
            }

            recordIter = records.iterator();
            while (recordIter.hasNext()) {
                ConsumerRecord<String, String> aa = recordIter.next();
                log.info("topic:[{}], partition:[{}], value:[{}]", aa.topic(), aa.partition(), aa.value());
            }
        }
    }

}
