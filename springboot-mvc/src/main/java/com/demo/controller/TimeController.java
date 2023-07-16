package com.demo.controller;

import com.demo.dto.Result;
import com.demo.entity.Time;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Date;

@RestController
public class TimeController {

    @GetMapping("/time")
    public Result time() {
        Time time = new Time();
        time.setDate(new Date());
        time.setLocalDateTime(LocalDateTime.now());
        return Result.success("查询成功", time);
    }
}
