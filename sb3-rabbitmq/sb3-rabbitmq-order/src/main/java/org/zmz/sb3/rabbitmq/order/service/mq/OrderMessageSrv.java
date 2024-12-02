package org.zmz.sb3.rabbitmq.order.service.mq;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;


@Component
public class OrderMessageSrv {

    private static final String EXCHANGE_KEY_NAME = "exchange";
    private static final String QUEUE_KEY_NAME = "queue";
    private static final String ROUTING_KEY_NAME = "routing-key";


    @Autowired
    RabbitProperties rabbitProperties;

    @Autowired
    OrderDetailMQConfigProperties orderDetailMQConfigProperties;

    public void handleOrderDetailMessage() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(rabbitProperties.getHost());
        factory.setPort(rabbitProperties.getPort());
        factory.setUsername(rabbitProperties.getUsername());
        factory.setPassword(rabbitProperties.getPassword());
        factory.setVirtualHost(rabbitProperties.getVirtualHost());

        try (
                Connection connection = factory.newConnection();
                Channel channel = connection.createChannel()
        ) {

            /**************餐厅微服务**********************/
            // 声明交换机
            channel.exchangeDeclare(
                    orderDetailMQConfigProperties.getRestaurant().getOrDefault(EXCHANGE_KEY_NAME, "order.to.restaurant"),
                    BuiltinExchangeType.DIRECT,
                    true,
                    false,
                    false,
                    null
            );
            // 声明队列
            channel.queueDeclare(
                    orderDetailMQConfigProperties.getCommon().getOrDefault(QUEUE_KEY_NAME, "queue.order"),
                    true,
                    false,
                    false,
                    null
            );
            // 绑定交换机队列
            channel.queueBind(
                    orderDetailMQConfigProperties.getCommon().getOrDefault(QUEUE_KEY_NAME, "queue.order"),
                    orderDetailMQConfigProperties.getRestaurant().getOrDefault(EXCHANGE_KEY_NAME, "order.to.restaurant"),
                    orderDetailMQConfigProperties.getCommon().getOrDefault(ROUTING_KEY_NAME, "key.order")
            );

            /**************骑手微服务**********************/
            // 声明交换机
            channel.exchangeDeclare(
                    orderDetailMQConfigProperties.getDeliveryman().getOrDefault(EXCHANGE_KEY_NAME, "order.to.deliveryman"),
                    BuiltinExchangeType.DIRECT,
                    true,
                    false,
                    false,
                    null
            );
            // 声明队列
            channel.queueDeclare(
                    orderDetailMQConfigProperties.getCommon().getOrDefault(QUEUE_KEY_NAME, "queue.order"),
                    true,
                    false,
                    false,
                    null
            );
            // 绑定交换机队列
            channel.queueBind(
                    orderDetailMQConfigProperties.getCommon().getOrDefault(QUEUE_KEY_NAME, "queue.order"),
                    orderDetailMQConfigProperties.getDeliveryman().getOrDefault(EXCHANGE_KEY_NAME, "order.to.deliveryman"),
                    orderDetailMQConfigProperties.getCommon().getOrDefault(ROUTING_KEY_NAME, "key.order")
            );
        }

    }

    @Component
    @ConfigurationProperties(prefix = "rmq.biz.srv")
    @Getter
    @Setter
    static class OrderDetailMQConfigProperties {
        private Map<String, String> common;
        private Map<String, String> restaurant;
        private Map<String, String> deliveryman;
    }

}
