package com.demo.dao;

import com.demo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {

    @Select("select id from user where open_id=#{openid}")
    User findByOpenId(String OpenId);

    @Insert("insert into user(open_id, nickname, avatar) values(#{openId}, #{nickname}, #{avatar})")
    Integer save(User user);

    @Update("update user set nickname=#{nickname}, avatar=#{avatar} where id=#{id}")
    Integer update(User user);
}
