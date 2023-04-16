package com.demo.event;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @date 2022-9-17
 **/
@Service
public class TestEventPublisher {

    @Resource
    private ApplicationContext applicationContext;

    public void publish(String msg) {
        applicationContext.publishEvent(new TestEvent(this, msg));
    }
}
