package com.demo.jwt.controller;

import com.demo.jwt.annotation.IgnoreToken;
import com.demo.jwt.dto.UserDTO;
import com.demo.jwt.entity.Token;
import com.demo.jwt.entity.User;
import com.demo.jwt.service.TokenService;
import com.demo.jwt.service.UserService;
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

    @GetMapping("/{id}")
    public User findUser(@PathVariable Integer id) {
        return userService.findById(id);
    }

    @IgnoreToken
    @PostMapping("/login")
    public Token login(@RequestBody UserDTO userDTO) throws Exception {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new Exception("username and password can't be empty");
        }
        User user = userService.findByUsernameAndPassword(username, password);
        if (user == null) {
            throw new Exception("username and password don't match");
        }
        return tokenService.createToken(user);
    }

    @IgnoreToken
    @PostMapping("/register")
    public Integer register(@RequestBody UserDTO userDTO) throws Exception {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new Exception("username and password can't be empty");
        }
        User user = userService.findByUsername(username);
        if (user != null) {
            throw new Exception("username has existed");
        }
        Integer res = userService.save(userDTO);
        if (res == 0) {
            throw new Exception("register failed");
        }
        return res;
    }
}
