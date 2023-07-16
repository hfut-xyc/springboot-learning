package com.demo.controller;


import com.demo.config.properties.JwtProperties;
import com.demo.pojo.entity.User;
import com.demo.utils.JwtUtils;
import com.demo.service.impl.UserServiceImpl;
import com.demo.pojo.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserServiceImpl userService;

    @Resource
    private JwtProperties jwtProperties;

    @PostMapping("/login")
    public Result login(@RequestBody User userDTO) {
        User user = userService.login(userDTO);

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        String token = JwtUtils.createToken(
                jwtProperties.getSecret(),
                jwtProperties.getTtl(),
                claims);
        return Result.success("登录成功", token);
    }

    @GetMapping("/me")
    public User findUser() {
        return null;
    }
}
