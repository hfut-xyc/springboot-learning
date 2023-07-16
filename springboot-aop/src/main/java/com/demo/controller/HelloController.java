package com.demo.controller;

import com.demo.annotations.CheckPoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

    @GetMapping("/hello")
    public void hello() {
        log.info("hello");
    }

    @CheckPoint
    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
