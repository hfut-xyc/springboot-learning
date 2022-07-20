package com.demo.basic.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;

import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AsyncService {

    @Async
    public void async() throws InterruptedException {
        Thread.sleep(2000);
        log.info("{}", Thread.currentThread().getName());
    }

}
