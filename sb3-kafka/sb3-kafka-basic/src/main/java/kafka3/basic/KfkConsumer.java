package kafka3.basic;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class KfkConsumer {

    public static final String TOPIC_NAME = "mytopic";

    public static void main(String[] args) {
        // 创建配置对象
        Map<String, Object> configMap = new HashMap<>(8);
        configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.110.201:9092");
        configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        configMap.put(ConsumerConfig.GROUP_ID_CONFIG, "zmz816");

        try (KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(configMap)) {
            kafkaConsumer.subscribe(Collections.singletonList(TOPIC_NAME));
            for (; ; ) {
                ConsumerRecords<String, String> datas = kafkaConsumer.poll(Duration.ofSeconds(10));
                datas.forEach(System.out::println);
            }
        }
    }
}
