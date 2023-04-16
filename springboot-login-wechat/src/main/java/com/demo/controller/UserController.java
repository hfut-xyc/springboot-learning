package com.demo.controller;

import com.demo.dto.LoginDTO;
import com.demo.entity.User;
import com.demo.service.TokenService;
import com.demo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private static final String URL = "https://api.weixin.qq.com/sns/jscode2session";
    private static final String APPID = "";
    private static final String SECRET = "";
    private static final String GRANT_TYPE = "authorization_code";

    @Resource
    private UserService userService;

    @Resource
    private TokenService tokenService;

    @Resource
    private RestTemplate restTemplate;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/{id}")
    public User findUser(@PathVariable String id) {
        return userService.findByOpenId(id);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginDTO loginDTO) throws Exception {
        String code = loginDTO.getCode();
        String nickname = loginDTO.getNickname();
        String avatar = loginDTO.getAvatar();
        if (StringUtils.isEmpty(code)) {
            throw new Exception("code cannot be empty");
        }
        String url = String.format("%s?appid=%s&secret=%s&js_code=%s&grant_type=%s",
                URL, APPID, SECRET, code, GRANT_TYPE);

        String result = restTemplate.getForObject(url, String.class);
        Map<String, String> resultMap = objectMapper.readValue(result, HashMap.class);
        String openId = resultMap.get("openid");

        User user = userService.findByOpenId(openId);
        if (user == null) {
            user = new User();
            user.setOpenId(openId);
            user.setNickname(nickname);
            user.setAvatar(avatar);
            Integer res = userService.save(user);
            if (res == 1) {
                log.info("register success");
            } else {
                throw new Exception("register fail");
            }
        } else {
            user.setNickname(nickname);
            user.setAvatar(avatar);
            Integer res = userService.update(user);
            if (res == 1) {
                log.info("update userInfo success");
            } else {
                throw new Exception("update userInfo fail");
            }
        }
        String token = tokenService.createToken(user);
        Map<String, String> data = new HashMap<>();
        data.put("token", token);
        data.put("nickname", nickname);
        data.put("avatar", avatar);
        return data;
    }

}
