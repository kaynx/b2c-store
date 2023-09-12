package com.org.admin.controller;

import com.org.admin.service.CategoryService;
import com.org.param.PageParam;
import com.org.pojo.Category;
import com.org.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 陈晨
 * @date 2023/6/1
 */
@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("list")
    public R pageList(PageParam pageParam) {
        return categoryService.pageList(pageParam);
    }

    @PostMapping("save")
    public R save(Category category){
        return categoryService.save(category);
    }

    @PostMapping("remove")
    public R remove(Integer categoryId){
        return categoryService.remove(categoryId);
    }
    @PostMapping("update")
    public R update(Category category){
        return categoryService.update(category);
    }
}
