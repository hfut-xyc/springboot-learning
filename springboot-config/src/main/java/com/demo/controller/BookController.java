package com.demo.controller;

import com.demo.bean.Book1;
import com.demo.bean.Book2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class BookController {

    @Resource
    private Book1 book1;

    @Resource
    private Book2 book2;

    @GetMapping("/book1")
    public Book1 book1() {
        return book1;
    }

    @GetMapping("/book2")
    public Book2 book2() {
        return book2;
    }
}
