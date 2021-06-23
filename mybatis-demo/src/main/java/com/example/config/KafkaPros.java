package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Properties;

/**
 * @author liuyzh
 * @date 2020/11/18
 */
@Configuration
public class KafkaPros {

    private static final Properties props = new Properties();

    @Value("${kafka.server}")
    private String server;

    @Value("${kafka.groupId}")
    private String groupId;

    @Value("${kafka.resetPolicy}")
    private String resetPolicy;

    @PostConstruct
    public void setup() {
        props.put("bootstrap.servers", server);
        props.put("group.id", groupId);
        props.put("enable.auto.commit",false);
        props.put("auto.offset.reset", resetPolicy);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    }

    public static Properties load() {
        return props;
    }

}
