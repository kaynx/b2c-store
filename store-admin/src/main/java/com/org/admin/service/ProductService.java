package com.org.admin.service;

import com.org.param.ProductSaveParam;
import com.org.param.ProductSearchParam;
import com.org.pojo.Product;
import com.org.utils.R;

/**
 * @author 陈晨
 * @date 2023/6/3
 */
public interface ProductService {
    /**
     * 全部商品查询和搜索商品
     *
     * @param productSearchParam
     * @return
     */
    R search(ProductSearchParam productSearchParam);

    /**
     * 保存商品
     *
     * @param productSaveParam
     * @return
     */
    R save(ProductSaveParam productSaveParam);

    /**
     * 更新商品
     *
     * @param product
     * @return
     */
    R update(Product product);

    /**
     * 删除商品
     *
     * @param productId
     * @return
     */
    R remove(Integer productId);
}
