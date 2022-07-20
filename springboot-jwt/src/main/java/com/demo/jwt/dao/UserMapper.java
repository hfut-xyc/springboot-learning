package com.demo.jwt.dao;

import com.demo.jwt.dto.UserDTO;
import com.demo.jwt.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

    @Select("select id, username, create_time from user where id=#{id}")
    User findById(Integer id);

    @Select("select * from user where username=#{username}")
    User findByUsername(String username);

    @Select("select * from user where username=#{username} and password=#{password}")
    User findByUsernameAndPassword(String username, String password);

    @Insert("insert into user(username, password) values(#{username}, #{password})")
    Integer save(UserDTO userDTO);
}
