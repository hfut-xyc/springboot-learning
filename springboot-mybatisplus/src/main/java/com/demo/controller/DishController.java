package com.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.config.Result;

import com.demo.entity.Category;
import com.demo.entity.Dish;
import com.demo.entity.DishDto;
import com.demo.service.CategoryService;
import com.demo.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜品管理
 */
@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController {

    @Resource
    private DishService dishService;

    @Resource
    private CategoryService categoryService;

    @GetMapping("/page")
    public Result<Page> page(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "name", required = false) String name) {
        // 构造分页构造器对象
        Page<Dish> pageInfo = new Page<>(page, pageSize);
        Page<DishDto> pageInfoDto = new Page<>();
        // 条件构造器
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        // 添加过滤条件
        queryWrapper.like(name != null, Dish::getName, name);
        // 执行分页查询
        dishService.page(pageInfo, queryWrapper);

        //对象拷贝
        BeanUtils.copyProperties(pageInfo, pageInfoDto, "records");
        List<Dish> records = pageInfo.getRecords();
        List<DishDto> list = records.stream().map((item) -> {
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item, dishDto);
            // 根据id查询分类对象
            Category category = categoryService.getById(item.getCategoryId());
            if (category != null) {
                dishDto.setCategoryName(category.getName());
            }
            return dishDto;
        }).collect(Collectors.toList());

        pageInfoDto.setRecords(list);
        return Result.success("查询成功", pageInfoDto);
    }

    @GetMapping("/{id}")
    public Result<Dish> get(@PathVariable Long id) {
        Dish dish = dishService.getById(id);
        return Result.success("查询成功", dish);
    }

    @PostMapping
    public Result<String> save(@RequestBody Dish dish) {
        log.debug(dish.toString());
        dishService.save(dish);
        return Result.success("新增菜品成功");
    }

    @PutMapping
    public Result<String> update(@RequestBody Dish dish) {
        log.info(dish.toString());
        dishService.updateById(dish);
        return Result.success("修改菜品成功");
    }
}
