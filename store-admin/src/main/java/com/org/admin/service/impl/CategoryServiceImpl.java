package com.org.admin.service.impl;

import com.org.admin.service.CategoryService;
import com.org.clients.CategoryClient;
import com.org.param.PageParam;
import com.org.pojo.Category;
import com.org.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author 陈晨
 * @date 2023/6/1
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryClient categoryClient;

    @Cacheable(value = "list.category", key = "#pageParam.currentPage+'-'+#pageParam.pageSize")
    @Override
    public R pageList(PageParam pageParam) {
        R ok = categoryClient.listPage(pageParam);
        log.info("CategoryServiceImpl.categoryService业务结束,结果{}", ok);
        return ok;
    }

    @CacheEvict(value = "list.category", allEntries = true)
    @Override
    public R save(Category category) {
        R ok = categoryClient.save(category);
        log.info("CategoryServiceImpl.save业务结束,结果{}", ok);
        return ok;
    }

    @CacheEvict(value = "list.category", allEntries = true)
    @Override
    public R remove(Integer categoryId) {
        R ok = categoryClient.remove(categoryId);
        log.info("CategoryServiceImpl.remove业务结束,结果{}", ok);
        return ok;
    }

    @CacheEvict(value = "list.category", allEntries = true)
    @Override
    public R update(Category category) {
        R ok = categoryClient.update(category);
        log.info("CategoryServiceImpl.update业务结束,结果{}", ok);
        return ok;
    }
}
