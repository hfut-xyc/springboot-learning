package com.demo.controller;


import com.demo.entity.User;
import com.demo.service.TokenService;
import com.demo.service.UserService;
import com.demo.common.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private TokenService tokenService;

    @PostMapping("/login")
    public Result<String> login(@RequestBody User loginForm) throws Exception {
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new Exception("username and password can't be empty");
        }
        User user = userService.findByUsername(username);
        if (user == null || !password.equals(user.getPassword())) {
            throw new Exception("failed to login");
        }
        String token = tokenService.createToken(user);
        return Result.success("login successfully", token);
    }

    @GetMapping("/{id}")
    public User findUser(@PathVariable Integer id) {
        return userService.findById(id);
    }
}
