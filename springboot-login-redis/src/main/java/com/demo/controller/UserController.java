package com.demo.controller;

import com.demo.pojo.entity.User;
import com.demo.pojo.vo.Result;
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
    public Result me() {
        return Result.success("success", UserHolder.getUser());
    }
}
