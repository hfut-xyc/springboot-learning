package com.basic.async;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class AsyncController {

    @Resource
    private AsyncService asyncService;

    @GetMapping("/async")
    public void async() throws InterruptedException {
        asyncService.async();
    }
}
