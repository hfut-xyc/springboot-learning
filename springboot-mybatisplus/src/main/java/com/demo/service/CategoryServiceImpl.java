package com.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.demo.entity.Category;
import com.demo.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService  {

    @Autowired
    private DishService dishService;

}
