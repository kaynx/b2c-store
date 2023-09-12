package com.org.product.controller;

import com.org.param.ProductSaveParam;
import com.org.pojo.Product;
import com.org.product.service.ProductService;
import com.org.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 陈晨
 * @date 2023/6/1
 */
@RestController
@RequestMapping("product")
public class ProductAdminController {
    @Autowired
    private ProductService productService;

    @PostMapping("admin/count")
    public Long adminCount(@RequestBody Integer categoryId) {
        return productService.adminCount(categoryId);
    }

    @PostMapping("admin/save")
    public R adminSave(@RequestBody ProductSaveParam productParam) {
        return productService.adminSave(productParam);
    }

    @PostMapping("admin/update")
    public R adminUpdate(@RequestBody Product product) {
        return productService.adminUpdate(product);
    }
    @PostMapping("admin/remove")
    public R adminRemove(@RequestBody Integer productId) {
        return productService.adminRemove(productId);
    }

}
