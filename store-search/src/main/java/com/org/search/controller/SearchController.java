package com.org.search.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.org.param.ProductSearchParam;
import com.org.pojo.Product;
import com.org.search.service.SearchService;
import com.org.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author 陈晨
 * @date 2023/5/22
 */
@RestController
@RequestMapping("search")
public class SearchController {
    @Autowired
    private SearchService searchService;

    @PostMapping("product")
    public R searchProduct(@RequestBody ProductSearchParam productSearchParam) throws JsonProcessingException {
        return searchService.search(productSearchParam);
    }

    /**
     * 同步调用，进行商品插入和更新
     * @param product
     * @return
     * @throws JsonProcessingException
     */
    @PostMapping("save")
    public R saveProduct(@RequestBody Product product) throws IOException {
        return searchService.saveProduct(product);
    }

    @PostMapping("remove")
    public R removeProduct(@RequestBody Integer productId) throws IOException {
        return searchService.removeProduct(productId);
    }
}
