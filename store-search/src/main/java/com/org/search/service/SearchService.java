package com.org.search.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.org.param.ProductSearchParam;
import com.org.pojo.Product;
import com.org.utils.R;

import java.io.IOException;

/**
 * @author 陈晨
 * @date 2023/5/22
 */
public interface SearchService {
    /**
     * 商品搜索
     *
     * @param productSearchParam
     * @return
     * @throws JsonProcessingException
     */
    R search(ProductSearchParam productSearchParam) throws JsonProcessingException;

    /**
     * 商品同步：插入和更新
     *
     * @param product
     * @return
     */
    R saveProduct(Product product) throws IOException;

    /**
     * 进行es库的商品删除
     *
     * @param productId
     */
    R removeProduct(Integer productId) throws IOException;
}
