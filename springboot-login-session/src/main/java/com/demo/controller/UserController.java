package com.demo.controller;


import com.demo.entity.dto.Result;
import com.demo.entity.User;
import com.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public User findUser(@PathVariable Integer id) {
        return userService.findById(id);
    }
}
