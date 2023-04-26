package com.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.entity.Dish;
import com.demo.entity.dto.DishDto;

public interface DishService extends IService<Dish> {

    Page<DishDto> pageDish(Integer page, Integer pageSize, String name);

}
