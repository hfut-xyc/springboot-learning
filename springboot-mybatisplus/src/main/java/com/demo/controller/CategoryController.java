package com.demo.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.demo.pojo.vo.Result;
import com.demo.pojo.entity.Category;
import com.demo.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 分类管理
 */
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/list")
    public Result list() {
        List<Category> list = categoryService.list();
        return Result.success("查询成功", list);
    }

    @GetMapping("/page")
    public Result page(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Page<Category> pageInfo = new Page<>(page, pageSize);
        categoryService.page(pageInfo);
        return Result.success("查询成功", pageInfo);
    }

    @PostMapping
    public Result save(@RequestBody Category category) {
        categoryService.save(category);
        return Result.success("新增分类成功");
    }

    @PutMapping
    public Result update(@RequestBody Category category) {
        categoryService.updateById(category);

        return Result.success("分类修改成功");
    }

    @DeleteMapping
    public Result delete(Long id) {
        // categoryService.removeById(id);
        // categoryService.remove(id);

        return Result.success("分类删除成功");
    }

}
