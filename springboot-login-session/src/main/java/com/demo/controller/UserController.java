package com.demo.controller;


import com.demo.entity.dto.Result;
import com.demo.entity.User;
import com.demo.service.UserService;
import com.demo.utils.UserHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User loginForm, HttpSession session) throws Exception {
        return userService.findByUsername(loginForm, session);
    }

    @GetMapping("/me")
    public Result findUser() {
        return Result.success("success", UserHolder.getUser());
    }
}
