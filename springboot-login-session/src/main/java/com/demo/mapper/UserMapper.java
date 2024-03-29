package com.demo.mapper;

import com.demo.pojo.entity.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("select id, username, password from tb_user where username=#{username}")
    User findByUsername(String username);

}
