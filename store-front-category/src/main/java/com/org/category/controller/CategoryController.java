package com.org.category.controller;

import com.org.category.service.CategoryService;
import com.org.param.PageParam;
import com.org.param.ProductHotParam;
import com.org.pojo.Category;
import com.org.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author 陈晨
 * @date 2023/5/18
 */
@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/promo/{categoryName}")
    public R getCategoryByCategoryName(@PathVariable String categoryName) {
        if (StringUtils.isEmpty(categoryName)) {
            return R.fail("类别名称为空，无法查询数据");
        }
        return categoryService.getCategoryByCategoryName(categoryName);
    }

    @PostMapping("hots")
    public R hotsCategory(@RequestBody @Validated ProductHotParam productHotParam, BindingResult result) {
        if (result.hasErrors()) {
            return R.fail("类别集合查询失败");
        }
        return categoryService.hotsCategory(productHotParam);
    }

    @GetMapping("list")
    public R list() {
        return categoryService.list();
    }

    @PostMapping("admin/list")
    public R listPage(@RequestBody PageParam pageParam){
        return categoryService.listPage(pageParam);
    }

    @PostMapping("admin/save")
    public R save(@RequestBody Category category){
        return categoryService.save(category);
    }

    @PostMapping("admin/remove")
    public R remove(@RequestBody Integer categoryId){
        return categoryService.remove(categoryId);
    }

    @PostMapping("admin/update")
    public R update(@RequestBody Category category){
        return categoryService.update(category);
    }
}
