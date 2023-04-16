package com.demo.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @date 2022-9-17
 **/

@Slf4j
@Component
public class TestEventListener1 {

    @Async
    @EventListener(TestEvent.class)
    public void listener(TestEvent event) throws InterruptedException {
        Thread.sleep(1000);
        log.debug(event.getMsg());
    }
}
