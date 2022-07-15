package com.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.demo")
@SpringBootApplication
public class JwtWechatApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(JwtWechatApplication.class, args);
    }
}
