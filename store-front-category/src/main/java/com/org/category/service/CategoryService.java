package com.org.category.service;

import com.org.param.PageParam;
import com.org.param.ProductHotParam;
import com.org.pojo.Category;
import com.org.utils.R;

/**
 * @author 陈晨
 * @date 2023/5/18
 */
public interface CategoryService {
    /**
     * 根据类别名称，查询类别兑现
     *
     * @return
     */
    R getCategoryByCategoryName(String categoryName);

    /**
     * 根据热门类别名称集合返回对应的id集合
     *
     * @param productHotParam
     * @return
     */
    R hotsCategory(ProductHotParam productHotParam);

    /**
     * 查询类别数据
     *
     * @return
     */
    R list();

    /**
     * 分页查询
     *
     * @param pageParam
     * @return
     */
    R listPage(PageParam pageParam);

    /**
     * 添加分类信息
     *
     * @param category
     * @return
     */
    R save(Category category);

    /**
     * 删除分类信息
     *
     * @param categoryId
     * @return
     */
    R remove(Integer categoryId);

    /**
     * 修改类别信息
     *
     * @param category
     * @return
     */
    R update(Category category);
}
