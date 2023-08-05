package com.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AmqpTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSimpleQueue() {
        String queueName = "simple.queue";
        //String message = "hello";
        Map<String, String> map = new HashMap<>();
        map.put("id", "1");
        map.put("name", "RabbitMQ");
        rabbitTemplate.convertAndSend(queueName, map);
    }

    @Test
    public void testWorkQueue() throws InterruptedException {
        String queueName = "work.queue";
        String message = "msg_";
        for (int i = 1; i <= 50; i++) {
            rabbitTemplate.convertAndSend(queueName, message + i);
            Thread.sleep(20);
        }
    }

    @Test
    public void testFanoutExchange() {
        String exchangeName = "fanout";
        String routingKey = "";
        String message = "hello";
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }

    @Test
    public void testDirectExchange() {
        String exchangeName = "direct";
        String routingKey = "A";
        String message = "hello";
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }

    @Test
    public void testTopicExchange() {
        String exchangeName = "topic";
        String routingKey = "china.weather";
        String message = "今天天气不错，我的心情好极了!";
        rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }
}
