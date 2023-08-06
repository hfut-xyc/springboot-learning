package com.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.demo.pojo.entity.Category;
import com.demo.mapper.CategoryMapper;
import com.demo.service.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
