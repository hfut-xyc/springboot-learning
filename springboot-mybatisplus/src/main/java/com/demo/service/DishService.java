package com.demo.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.pojo.entity.Dish;
import com.demo.pojo.vo.DishVO;

public interface DishService extends IService<Dish> {

    Page<DishVO> pageDish(Integer page, Integer pageSize, String name);

}
