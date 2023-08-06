package com.demo.config.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Map;

@Component
public class WorkQueueListener {

    @RabbitListener(queues = "simple.queue")
    public void listenSimpleQueue(Map<String, Object> msg) {
        System.out.println("接收到 simple.queue 的消息：" + msg);
    }

    @RabbitListener(queues = "work.queue")
    public void listenWorkQueue1(String msg) throws InterruptedException {
        System.out.println(LocalTime.now() + "消费者1收到消息：【" + msg + "】");
        Thread.sleep(20);
    }

    @RabbitListener(queues = "work.queue")
    public void listenWorkQueue2(String msg) throws InterruptedException {
        System.err.println(LocalTime.now() + "消费者2收到消息：【" + msg + "】");
        Thread.sleep(200);
    }

}
