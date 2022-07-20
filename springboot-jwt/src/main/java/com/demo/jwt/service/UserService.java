package com.demo.jwt.service;

import com.demo.jwt.dao.UserMapper;
import com.demo.jwt.dto.UserDTO;
import com.demo.jwt.entity.User;
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

    public User findByUsernameAndPassword(String username, String password) {
        return userMapper.findByUsernameAndPassword(username, password);
    }

    public Integer save(UserDTO userDTO) {
        return userMapper.save(userDTO);
    }
}
