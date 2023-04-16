package com.demo.service;

import com.demo.entity.User;
import com.demo.mapper.UserMapper;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    public Integer save(User user) {
        return userMapper.save(user);
    }
}
