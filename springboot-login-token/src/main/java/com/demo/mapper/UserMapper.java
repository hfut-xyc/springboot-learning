package com.demo.mapper;

import com.demo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("select id, username, password from user where username=#{username}")
    User findByUsername(String username);
}
