package com.example.utils.kafka;

import com.example.config.KafkaPros;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @author liuyzh
 * @date 2020/11/18
 */
public class KafkaUtil {

    private static final Logger log = LoggerFactory.getLogger(KafkaUtil.class);

    private static KafkaConsumer<String, String> consumer = null;

    private KafkaUtil() {

    }

//    /**
//     * 静态内部类
//     */
//    private static class InstanceHolder {
//        // 不会在外部类初始化时就直接加载，只有当调用了getInstance方法时才会静态加载，线程安全。
//        private static KafkaUtil instance = new KafkaUtil();
//    }
//
//    /**
//     * 单例模式，获取Kafka consumer实例
//     */
//    public static KafkaUtil getInstance() {
//        return InstanceHolder.instance;
//    }

    public static KafkaConsumer<String, String> getConsumer() {
        Properties props = KafkaPros.load();
        consumer = new KafkaConsumer<>(props);
        log.info("Kafka consumer init success!");
        return consumer;
    }

}
