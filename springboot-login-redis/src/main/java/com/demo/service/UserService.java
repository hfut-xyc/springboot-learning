package com.demo.service;

import com.demo.pojo.entity.User;
import com.demo.pojo.vo.Result;
import com.demo.mapper.UserMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static com.demo.utils.RedisConstants.LOGIN_USER_KEY;
import static com.demo.utils.RedisConstants.LOGIN_USER_TTL;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private static final ObjectMapper mapper = new ObjectMapper();

    public Result login(User loginForm) throws Exception {
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new Exception("用户名和密码不能为空");
        }
        User user = userMapper.findByUsername(username);
        if (user == null || !password.equals(user.getPassword())) {
            throw new Exception("登录失败");
        }
        user.setPassword("");

        String token = UUID.randomUUID().toString();
        String key = LOGIN_USER_KEY + token;
        String value = mapper.writeValueAsString(user);

        stringRedisTemplate.opsForValue().set(key, value);
        stringRedisTemplate.expire(key, LOGIN_USER_TTL, TimeUnit.MINUTES);
        return Result.success("登录成功", token);
    }
}
