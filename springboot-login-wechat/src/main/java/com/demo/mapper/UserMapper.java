package com.demo.mapper;

import com.demo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {

    @Select("select id from user_wechat where open_id=#{openid}")
    User findByOpenId(String OpenId);

    @Insert("insert into user_wechat(open_id, nickname, avatar) values(#{openId}, #{nickname}, #{avatar})")
    Integer save(User user);

    @Update("update user_wechat set nickname=#{nickname}, avatar=#{avatar} where id=#{id}")
    Integer update(User user);
}
