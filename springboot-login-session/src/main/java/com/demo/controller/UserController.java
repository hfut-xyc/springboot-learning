package com.demo.controller;


import com.demo.pojo.dto.Result;
import com.demo.pojo.entity.User;
import com.demo.service.UserService;
import com.demo.utils.UserHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    public Result me() {
        return Result.success("success", UserHolder.getUser());
    }

    @GetMapping("/me1")
    public Result me1(HttpSession session) {
        return Result.success("success", session.getAttribute("user"));
    }

    @GetMapping("/me2")
    public Result me2() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
        HttpSession session = httpServletRequest.getSession();
        return Result.success("success", session.getAttribute("user"));
    }
}
