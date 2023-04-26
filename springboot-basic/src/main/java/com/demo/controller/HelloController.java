package com.demo.controller;

import com.demo.entity.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/test")
    public Book test() {
        Book book = new Book();
        book.setDate(new Date());
        book.setLocalDateTime(LocalDateTime.now());
        return book;
    }
}
