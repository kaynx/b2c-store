package com.org.product.controller;

import com.org.pojo.Product;
import com.org.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 陈晨
 * @date 2023/5/21
 */
@RestController
@RequestMapping("product")
public class ProductSearchController {
    @Autowired
    private ProductService productService;

    @GetMapping("list")
    public List<Product> list(){
        return productService.allList();
    }
}
