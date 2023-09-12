package com.org.clients;

import com.org.param.PageParam;
import com.org.param.ProductHotParam;
import com.org.pojo.Category;
import com.org.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 陈晨
 * @date 2023/5/19
 */
@FeignClient("category-service")
public interface CategoryClient {
    /**
     * 根据类别名称，查询类别对象
     *
     * @param categoryName
     * @return
     */
    @GetMapping("category/promo/{categoryName}")
    R getCategoryByCategoryName(@PathVariable String categoryName);

    /**
     * 根据热门类别名称集合返回对应的id集合
     *
     * @param productHotParam
     * @return
     */
    @PostMapping("/category/hots")
    R hotsCategory(@RequestBody @Validated ProductHotParam productHotParam);

    @GetMapping("/category/list")
    R list();

    @PostMapping("/category/admin/list")
    R listPage(@RequestBody PageParam pageParam);

    @PostMapping("/category/admin/save")
    R save(@RequestBody Category category);

    @PostMapping("/category/admin/remove")
    R remove(@RequestBody Integer categoryId);

    @PostMapping("/category/admin/update")
    R update(@RequestBody Category category);
}
