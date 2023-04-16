package com.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.demo")
@SpringBootApplication
public class WechatApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(WechatApplication.class, args);
    }
}
