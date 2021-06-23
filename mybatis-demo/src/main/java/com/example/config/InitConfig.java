package com.example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Properties;

@Configuration
public class InitConfig {

    private static final Properties pros = new Properties();

    @Value("${kafka.server}")
    private String kafkaServer;

    @PostConstruct
    public void setup() {
        pros.setProperty("kafkaServer", kafkaServer);
    }

    public static Properties load() {
        return pros;
    }

}
