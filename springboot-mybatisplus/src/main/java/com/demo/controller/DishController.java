package com.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.pojo.entity.Dish;
import com.demo.pojo.vo.DishVO;
import com.demo.pojo.vo.Result;
import com.demo.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 菜品管理
 */
@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController {

    @Resource
    private DishService dishService;


    @GetMapping("/page")
    public Result page(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
            @RequestParam(value = "name", required = false) String name) {

        Page<DishVO> dishDtoPage = dishService.pageDish(page, pageSize, name);
        return Result.success("查询成功", dishDtoPage);
    }

    @GetMapping("/{id}")
    public Result get(@PathVariable Long id) {
        Dish dish = dishService.getById(id);
        return Result.success("查询成功", dish);
    }

    @PostMapping
    public Result save(@RequestBody Dish dish) {
        log.debug(dish.toString());
        dishService.save(dish);
        return Result.success("新增菜品成功");
    }

    @PutMapping
    public Result update(@RequestBody Dish dish) {
        log.info(dish.toString());
        dishService.updateById(dish);
        return Result.success("修改菜品成功");
    }
}
