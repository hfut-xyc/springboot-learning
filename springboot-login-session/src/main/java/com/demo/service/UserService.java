package com.demo.service;

import com.demo.entity.User;
import com.demo.entity.dto.Result;
import com.demo.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    public Result findByUsername(User loginForm, HttpSession session) throws Exception {
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new Exception("用户名和密码不能为空");
        }
        User user = userMapper.findByUsername(username);
        if (user == null || !password.equals(user.getPassword())) {
            throw new Exception("登录失败");
        }
        session.setAttribute("uid", user.getId());
        return Result.success("登录成功", null);
    }

}
