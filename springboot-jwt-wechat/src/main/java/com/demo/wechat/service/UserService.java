package com.demo.wechat.service;

import com.demo.wechat.dao.UserMapper;
import com.demo.wechat.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public User findByOpenId(String OpenId) {
        return userMapper.findByOpenId(OpenId);
    }

    public Integer save(User user) {
        return userMapper.save(user);
    }

    public Integer update(User user) {
        return userMapper.update(user);
    }
}
