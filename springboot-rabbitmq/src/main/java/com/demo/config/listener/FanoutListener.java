package com.demo.config.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class FanoutListener {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("fanout.queue1"),
            exchange = @Exchange(name = "fanout", type = ExchangeTypes.FANOUT)
    ))
    public void listenFanoutQueue1(String msg) {
        System.out.println("消费者接收到fanout.queue1的消息：【" + msg + "】");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("fanout.queue2"),
            exchange = @Exchange(name = "fanout", type = ExchangeTypes.FANOUT)
    ))
    public void listenFanoutQueue2(String msg) {
        System.out.println("消费者接收到fanout.queue2的消息：【" + msg + "】");
    }

}
