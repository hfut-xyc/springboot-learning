package com.demo.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;

import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

@Slf4j
@Service
public class TaskService {

    public void sync() {
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        log.debug("time = {}ms", end - start);
    }

    @Async
    public Future<String> async() {
        long start = System.currentTimeMillis();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        log.debug("time = {}ms", end - start);
        return AsyncResult.forValue("result");
    }

    //@Scheduled(fixedRate = 1000)
    //public void schedule1() {
    //    log.info("fixedRate");
    //}

    //@Scheduled(fixedDelay = 1000)
    //public void schedule2() {
    //    log.info("fixedDelay");
    //}

    //@Scheduled(cron = "* * ")
    //public void schedule3() {
    //    log.info("{}", Thread.currentThread().getName());
    //}
}
