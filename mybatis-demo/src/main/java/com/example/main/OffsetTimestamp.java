package com.example.main;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndTimestamp;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * 根据时间戳获取对应offset的工具脚本
 *
 * @author liuyzh
 * @date 2020/9/16
 */
public class OffsetTimestamp {

    private static final Logger log = LoggerFactory.getLogger(OffsetTimestamp.class);

    @Parameter(names = {"--help", "-h"}, description = "查看参数选项", order = 0, help = true)
    private boolean help;

    @Parameter(names = {"--broker-list"}, description = "REQUIRED: The list of hostname and port of the server to connect to.", order = 1, required = true)
    private String address;

    @Parameter(names = {"--topic"}, description = "REQUIRED: The topic to get offset from.", order = 2, required = true)
    private String topic;

    @Parameter(names = {"--group"}, description = "消费者组", order = 3)
    private String group = "ots-group";

    @Parameter(names = {"--time"}, description = "value: <Long: timestamp/-1(latest)/-2(earliest), timestamp of the offsets", order = 4)
    private Long time;


    private static void usage(JCommander commander) {
        StringBuilder sb = new StringBuilder();
        sb.append("Description: 根据时间戳获取对应的offset.\n");
        sb.append("             注: 带\"*\"为必填选项.\n");
        commander.usage(sb);
        System.out.println(sb.toString());
        System.out.flush();
    }

    private static KafkaConsumer<String, String> consumer;

    public static void main(String[] args) {
        OffsetTimestamp offsetTimestamp = new OffsetTimestamp();
        JCommander commander = new JCommander(offsetTimestamp);
        try {
            commander.parse(args);
        } catch (Exception e) {
            usage(commander);
            return;
        }

        if (offsetTimestamp.help) {
            usage(commander);
            return;
        }

        // 获取args参数值
        String topic = offsetTimestamp.topic;
        String group = offsetTimestamp.group;
        String address = offsetTimestamp.address;
        Long time = offsetTimestamp.time;

        Set<TopicPartition> assignment;
        Map<TopicPartition, OffsetAndTimestamp> offsets;

        init(address, group);

        try {
            consumer.subscribe(Arrays.asList(topic));

            // 主要逻辑
            Map<TopicPartition, Long> timestampToSearch = new HashMap<>();
            assignment = new HashSet<>();
            // 在poll()方法内部执行分区分配逻辑，该循环确保分区已被分配。
            // 当分区消息为0时进入此循环，如果不为0，则说明已经成功分配到了分区。
            while (assignment.isEmpty()) {
                consumer.poll(100);
                // assignment()方法是用来获取消费者所分配到的分区消息的
                // assignment的值为：topic-demo-3, topic-demo-0, topic-demo-2, topic-demo-1
                assignment = consumer.assignment();
            }
            for (TopicPartition tp : assignment) {
                // 设置查询分区时间戳的条件：获取当前时间前一天之后的消息
                timestampToSearch.put(tp, time);
            }

            if (time != null && time == -2) {
                timestampToSearch = consumer.beginningOffsets(assignment);
                for (Map.Entry<TopicPartition, Long> entry : timestampToSearch.entrySet()) {
                    String topicName = entry.getKey().topic();
                    Integer partitionNum = entry.getKey().partition();
                    System.out.println("\nResult: ");
                    System.out.println(String.format("\t%s:%s:%d", topicName, partitionNum, entry.getValue()));
                }
            } else if (time != null && time > 0) {
                offsets = consumer.offsetsForTimes(timestampToSearch);
                for (TopicPartition tp : assignment) {
                    // 获取该分区的offset以及timestamp
                    OffsetAndTimestamp offsetAndTimestamp = offsets.get(tp);
                    // 如果offsetAndTimestamp不为null，则证明当前分区有符合时间戳条件的消息
                    if (offsetAndTimestamp != null) {
                        log.info("[{}]对应的offset为：[{}]", time, offsetAndTimestamp.offset());
                        System.out.println("\nResult: ");
                        System.out.println(String.format("\t%s:%s:%d", tp.topic(), tp.partition(), offsetAndTimestamp.offset()));
                    } else {
                        log.warn("[{}]无效。", time);
                        System.out.println("\nResult: ");
                        System.out.println(String.format("\t%s:%s: ", tp.topic(), tp.partition()));
                    }
                }
            } else {
                timestampToSearch = consumer.endOffsets(assignment);
                for (Map.Entry<TopicPartition, Long> entry : timestampToSearch.entrySet()) {
                    String topicName = entry.getKey().topic();
                    Integer partitionNum = entry.getKey().partition();
                    System.out.println("\nResult: ");
                    System.out.println(String.format("\t%s:%s:%d", topicName, partitionNum, entry.getValue()));
                }
            }
        } finally {
            if (consumer != null) {
                consumer.close();
            }
        }
    }

    private static KafkaConsumer<String, String> init(String address, String group) {
        Properties props = new Properties();

        props.put("bootstrap.servers", address);
        props.put("group.id", group);
        props.put("auto.offset.reset", "latest");
        props.put("enable.auto.commit", false);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        consumer = new KafkaConsumer<>(props);
        log.info("Kafka consumer init success!");
        return consumer;
    }

}
