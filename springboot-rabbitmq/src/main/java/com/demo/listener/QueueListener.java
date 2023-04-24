package com.demo.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.Map;

@Component
public class QueueListener {

    //@RabbitListener(queues = "simple.queue")
    //public void listenWorkQueue1(String msg) throws InterruptedException {
    //    System.out.println("消费者1接收到消息：【" + msg + "】" + LocalTime.now());
    //    Thread.sleep(20);
    //}
    //
    //@RabbitListener(queues = "simple.queue")
    //public void listenWorkQueue2(String msg) throws InterruptedException {
    //    System.err.println("消费者2........接收到消息：【" + msg + "】" + LocalTime.now());
    //    Thread.sleep(200);
    //}

}
