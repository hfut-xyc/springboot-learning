package com.demo.controller;

import com.demo.event.TestEventPublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @date 2022-9-17
 **/
@Slf4j
@RestController
public class EventController {

    @Resource
    private TestEventPublisher testEventPublisher;

    @GetMapping("/event")
    public void event() {
        long start = System.currentTimeMillis();
        for (int i = 1; i <= 4; i++) {
            testEventPublisher.publish("msg" + i);
        }
        long end = System.currentTimeMillis();
        log.debug("time = {}ms", end - start);
    }
}
