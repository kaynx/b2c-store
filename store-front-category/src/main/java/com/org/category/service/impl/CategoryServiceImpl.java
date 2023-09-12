package com.org.category.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.org.category.mapper.CategoryMapper;
import com.org.category.service.CategoryService;
import com.org.clients.ProductClient;
import com.org.param.PageParam;
import com.org.param.ProductHotParam;
import com.org.pojo.Category;
import com.org.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 陈晨
 * @date 2023/5/18
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ProductClient productClient;

    @Override
    public R getCategoryByCategoryName(String categoryName) {
        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.eq("category_name", categoryName);
        Category category = categoryMapper.selectOne(categoryQueryWrapper);
        if (category == null) {
            log.info("CategoryServiceImpl.getProductsByCategoryName()业务结束,结果:{}", "类别查询失败");
            return R.fail("类别查询失败");
        }
        log.info("CategoryServiceImpl.getProductsByCategoryName()业务结束,结果:{}", "类别查询成功");
        return R.ok("类别查询成功", category);
    }

    @Override
    public R hotsCategory(ProductHotParam productHotParam) {
        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.in("category_name", productHotParam.getCategoryName());
        categoryQueryWrapper.select("category_id");
        List<Object> ids = categoryMapper.selectObjs(categoryQueryWrapper);
        R ok = R.ok("类别查询成功", ids);
        log.info("CategoryServiceImpl.hotsCategory()业务结束,结果:{}", ok);
        return ok;
    }

    @Override
    public R list() {
        List<Category> categories = categoryMapper.selectList(null);
        R ok = R.ok("全部类别查询成功", categories);
        log.info("CategoryServiceImpl.list()业务结束,结果:{}", ok);
        return ok;

    }

    @Override
    public R listPage(PageParam pageParam) {
        IPage<Category> page = new Page<>(pageParam.getCurrentPage(), pageParam.getPageSize());
        page = categoryMapper.selectPage(page, null);
        log.info("CategoryServiceImpl.listPage()业务结束,结果:{}", page.toString());
        return R.ok("类别分页数据查询成功", page.getRecords(), page.getTotal());

    }

    @Override
    public R save(Category category) {
        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.eq("category_name", category.getCategoryName());
        Long aLong = categoryMapper.selectCount(categoryQueryWrapper);
        if (aLong > 0) {
            return R.fail("类别已经存在添加失败");
        }
        int rows = categoryMapper.insert(category);
        log.info("CategoryServiceImpl.save()业务结束,结果:{}", rows);
        return R.ok("类别添加成功");
    }

    @Override
    public R remove(Integer categoryId) {

        Long aLong = productClient.adminCount(categoryId);
        if (aLong > 0) {
            return R.fail("类别删除失败，有" + aLong + "件商品正在被引用");
        }
        int rows = categoryMapper.deleteById(categoryId);
        log.info("CategoryServiceImpl.remove()业务结束,结果:{}", rows);
        return R.ok("类别删除成功");

    }

    @Override
    public R update(Category category) {
        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.eq("category_name", category.getCategoryName());
        Long aLong = categoryMapper.selectCount(categoryQueryWrapper);
        if (aLong > 0) {
            return R.fail("类别已经存在修改失败");
        }
        int rows = categoryMapper.updateById(category);
        log.info("CategoryServiceImpl.update()业务结束,结果:{}", rows);
        return R.ok("类别修改成功");

    }
}
