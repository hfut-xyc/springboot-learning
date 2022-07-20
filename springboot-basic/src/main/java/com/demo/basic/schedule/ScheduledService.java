package com.demo.basic.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ScheduledService {

    @Scheduled(fixedRate = 1000)
    public void schedule1() {
        log.info("fixedRate {}", Thread.currentThread().getName());
    }

    @Scheduled(fixedDelay = 1000)
    public void schedule2() {
        log.info("fixedDelay {}", Thread.currentThread().getName());
    }

//    @Scheduled(cron = "* * ")
//    public void schedule3() {
//        log.info("{}", Thread.currentThread().getName());
//    }
}
