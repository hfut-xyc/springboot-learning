package com.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.dto.PageResult;
import com.demo.dto.RequestParams;
import com.demo.entity.Hotel;
import com.demo.entity.HotelDoc;

import java.util.List;
import java.util.Map;

/**
 * @date 2022-9-29
 **/
public interface IHotelService extends IService<Hotel> {

    PageResult<HotelDoc> search(RequestParams params);

    Map<String, List<String>> getFilters(RequestParams params);
}
