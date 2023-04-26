package com.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.entity.Category;
import com.demo.entity.Dish;
import com.demo.entity.dto.DishDto;
import com.demo.mapper.CategoryMapper;
import com.demo.mapper.DishMapper;
import com.demo.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Resource
    private CategoryMapper categoryMapper;

    @Override
    public Page<DishDto> pageDish(Integer page, Integer pageSize, String name) {
        // 构造分页构造器对象
        Page<Dish> dishPage = new Page<>(page, pageSize);
        // 条件构造器
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        // 添加过滤条件
        queryWrapper.like(name != null, Dish::getName, name);
        // 执行分页查询
        this.page(dishPage, queryWrapper);

        //对象拷贝
        Page<DishDto> dishDtoPage = new Page<>();
        BeanUtils.copyProperties(dishPage, dishDtoPage, "records");
        List<Dish> records = dishPage.getRecords();
        List<DishDto> list = records.stream().map((item) -> {
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item, dishDto);
            // 根据id查询分类对象
            Category category = categoryMapper.selectById(item.getCategoryId());
            if (category != null) {
                dishDto.setCategoryName(category.getName());
            }
            return dishDto;
        }).collect(Collectors.toList());

        dishDtoPage.setRecords(list);
        return dishDtoPage;
    }
}
