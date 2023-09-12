package com.org.admin.service;

import com.org.param.PageParam;
import com.org.pojo.Category;
import com.org.utils.R;

/**
 * @author 陈晨
 * @date 2023/6/1
 */
public interface CategoryService {
    /**
     * 分页查询分类
     *
     * @param pageParam
     * @return
     */
    R pageList(PageParam pageParam);

    /**
     * 分类数据添加
     *
     * @param category
     * @return
     */
    R save(Category category);

    /**
     * 根据分类id删除分类数据
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
