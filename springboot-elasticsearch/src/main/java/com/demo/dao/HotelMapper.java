package com.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.entity.Hotel;
import org.apache.ibatis.annotations.Mapper;

/**
 * @date 2022-9-29
 **/
@Mapper
public interface HotelMapper extends BaseMapper<Hotel> {
}

