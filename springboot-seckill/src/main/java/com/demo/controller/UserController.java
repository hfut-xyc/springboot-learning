package com.demo.controller;

import com.demo.entity.User;
import com.demo.entity.dto.Result;
import com.demo.service.UserService;
import com.demo.utils.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user) throws Exception {
        return userService.login(user);
    }

    @GetMapping("/me")
    public Result findUser() {
        return Result.success("success", UserHolder.getUser());
    }
}
