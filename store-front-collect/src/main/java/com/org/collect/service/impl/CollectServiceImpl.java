package com.org.collect.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.org.clients.ProductClient;
import com.org.collect.mapper.CollectMapper;
import com.org.collect.service.CollectService;
import com.org.param.ProductCollectParam;
import com.org.param.ProductIdsParam;
import com.org.pojo.Collect;
import com.org.pojo.Product;
import com.org.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.Current;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 陈晨
 * @date 2023/5/26
 */
@Service
@Slf4j
public class CollectServiceImpl implements CollectService {
    @Autowired
    private CollectMapper collectMapper;
    @Autowired
    private ProductClient productClient;

    @Override
    public R save(Collect collect) {
        QueryWrapper<Collect> collectQueryWrapper = new QueryWrapper<>();
        collectQueryWrapper.eq("user_id", collect.getUserId());
        collectQueryWrapper.eq("product_id", collect.getProductId());
        Long count = collectMapper.selectCount(collectQueryWrapper);
        if (count > 0) {
            return R.fail("收藏夹已存在，无需二次添加");
        }
        collect.setCollectTime(System.currentTimeMillis());
        int row = collectMapper.insert(collect);
        log.info("CollectServiceImpl.save()业务结束,结果:{}", row);
        return R.ok("商品收藏成功");
    }

    @Override
    public R list(Integer userId) {
        QueryWrapper<Collect> collectQueryWrapper = new QueryWrapper<>();
        collectQueryWrapper.eq("user_id", userId);
        collectQueryWrapper.select("product_id");
        List<Object> idsObject = collectMapper.selectObjs(collectQueryWrapper);

        ProductCollectParam productCollectParam = new ProductCollectParam();

        List<Integer> ids = new ArrayList<>();
        for (Object o : idsObject) {
            ids.add((Integer) o);
        }

        productCollectParam.setProductIds(ids);
        R ok = productClient.productIds(productCollectParam);
        log.info("CollectServiceImpl.list()业务结束,结果:{}", ok);
        return ok;


    }

    @Override
    public R remove(Collect collect) {
        QueryWrapper<Collect> collectQueryWrapper = new QueryWrapper<>();
        collectQueryWrapper.eq("user_id", collect.getUserId());
        collectQueryWrapper.eq("product_id", collect.getProductId());
        int rows = collectMapper.delete(collectQueryWrapper);
        log.info("CollectServiceImpl.remove()业务结束,结果:{}", rows);
        return R.ok("删除收藏成功");
    }

    @Override
    public R removeByPid(Integer productId) {
        QueryWrapper<Collect> collectQueryWrapper = new QueryWrapper<>();

        collectQueryWrapper.eq("product_id", productId);
        int rows = collectMapper.delete(collectQueryWrapper);
        log.info("CollectServiceImpl.removeByPid()业务结束,结果:{}", rows);
        return R.ok("收藏商品删除成功");

    }
}
