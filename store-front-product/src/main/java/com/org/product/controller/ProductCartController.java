package com.org.product.controller;

import com.org.param.CartParam;
import com.org.param.ProductCollectParam;
import com.org.param.ProductIdParam;
import com.org.pojo.Product;
import com.org.product.service.ProductService;
import com.org.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈晨
 * @date 2023/5/28
 */
@RestController
@RequestMapping("product")
public class ProductCartController {
    @Autowired
    private ProductService productService;

    @PostMapping("cart/detail")
    public Product cartDetail(@RequestBody @Validated ProductIdParam productIdParam, BindingResult result) {
        if (result.hasErrors()) {
            return null;
        }
        R r = productService.detail(productIdParam);
        Product product = (Product) r.getData();
        return product;
    }

    @PostMapping("cart/list")
    public List<Product> list(@RequestBody @Validated ProductCollectParam productCollectParam, BindingResult result) {
        if (result.hasErrors()) {
            return new ArrayList<Product>();
        }
        return productService.cartList(productCollectParam.getProductIds());
    }
}
