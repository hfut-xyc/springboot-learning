package com.demo.service.impl;

import com.demo.pojo.entity.User;
import com.demo.mapper.UserMapper;

import com.demo.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    public User login(User userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new RuntimeException("用户名和密码不能为空");
        }

        User user = userMapper.findByUsername(username);
        if (user == null || !password.equals(user.getPassword())) {
            throw new RuntimeException("登录失败");
        }
        return user;
    }
}
