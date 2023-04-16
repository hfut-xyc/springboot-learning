package com.demo.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
@RestController
public class TaskController {

    @Resource
    private TaskService taskService;

    @GetMapping("/sync")
    public void sync() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 4; i++) {
            taskService.sync();
        }
        long end = System.currentTimeMillis();
        log.debug("time = {}ms", end - start);
    }

    @GetMapping("/async")
    public void async() throws InterruptedException, ExecutionException {
        long start = System.currentTimeMillis();
        List<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            futures.add(taskService.async());
        }
        for (Future future : futures) {
            future.get();
        }
        long end = System.currentTimeMillis();
        log.debug("time = {}ms", end - start);
    }
}
