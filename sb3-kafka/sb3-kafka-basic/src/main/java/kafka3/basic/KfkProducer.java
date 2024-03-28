package kafka3.basic;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.Map;

public class KfkProducer {
    public static void main(String[] args) {
        // 创建配置对象
        Map<String, Object> configMap = new HashMap<>(8);
        configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.110.211:9092");
        configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(configMap);
        for (int i = 1; i <= 10; i++) {
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>("test2", "k" + i, "v" + i);
            // 创建 Kafka 生产者 将数据发送到 Broker
            kafkaProducer.send(producerRecord);
        }

        kafkaProducer.close();
    }
}
