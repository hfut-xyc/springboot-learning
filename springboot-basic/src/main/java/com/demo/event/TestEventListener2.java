package com.demo.event;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @date 2022-9-17
 **/

@Slf4j
@Component
public class TestEventListener2 implements ApplicationListener<TestEvent> {

    @Async
    @SneakyThrows
    @Override
    public void onApplicationEvent(TestEvent event) {
        Thread.sleep(1000);
        log.debug(event.getMsg());
    }
}
