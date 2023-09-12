package com.org.admin.service.impl;

import com.org.admin.service.ProductService;
import com.org.clients.ProductClient;
import com.org.clients.SearchClient;
import com.org.param.ProductSaveParam;
import com.org.param.ProductSearchParam;
import com.org.pojo.Product;
import com.org.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 陈晨
 * @date 2023/6/3
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired
    private SearchClient searchClient;
    @Autowired
    private ProductClient productClient;

    @Override
    public R search(ProductSearchParam productSearchParam) {
        R ok = searchClient.searchProduct(productSearchParam);
        log.info("ProductServiceImpl.search业务结束，结果{}", ok);
        return ok;
    }

    @Override
    public R save(ProductSaveParam productSaveParam) {
        R ok = productClient.adminSave(productSaveParam);
        log.info("ProductServiceImpl.save()业务结束，结果{}", ok);
        return ok;
    }

    @Override
    public R update(Product product) {
        R ok = productClient.adminUpdate(product);
        log.info("ProductServiceImpl.update()业务结束，结果{}", ok);
        return ok;
    }

    @Override
    public R remove(Integer productId) {
        R ok = productClient.adminRemove(productId);
        log.info("ProductServiceImpl.remove()业务结束，结果{}", ok);
        return ok;
    }
}
