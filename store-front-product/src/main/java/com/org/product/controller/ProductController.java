package com.org.product.controller;

import com.org.param.*;
import com.org.product.service.ProductService;
import com.org.utils.R;
import org.apache.coyote.OutputBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 陈晨
 * @date 2023/5/19
 */
@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("promo")
    public R promo(@RequestBody @Validated ProductPromoParam productPromoParam, BindingResult result) {
        if (result.hasErrors()) {
            return R.fail("数据查询失败");
        }
        return productService.promo(productPromoParam.getCategoryName());
    }

    @PostMapping("hots")
    public R hots(@RequestBody @Validated ProductHotParam productHotParam, BindingResult result){
        if (result.hasErrors()) {
            return R.fail("数据查询失败");
        }
        return productService.hots(productHotParam);
    }

    @PostMapping("category/list")
    public R categoryList(){
        return productService.categoryList();
    }

    @PostMapping("bycategory")
    public R byCategory(@RequestBody @Validated ProductIdsParam productIdsParam, BindingResult result){
        if (result.hasErrors()){
            return R.fail("商品类别查询失败");
        }
        return productService.byCategory(productIdsParam);
    }

    @PostMapping("all")
    public R all(@RequestBody @Validated ProductIdsParam productIdsParam, BindingResult result){
        if (result.hasErrors()){
            return R.fail("商品类别查询失败");
        }
        return productService.byCategory(productIdsParam);
    }

    @PostMapping("detail")
    public R detail(@RequestBody @Validated ProductIdParam productIdParam,BindingResult result){
        if (result.hasErrors()){
            return R.fail("商品详情查询失败");
        }
        return productService.detail(productIdParam);
    }

    @PostMapping("pictures")
    public R productPictures(@RequestBody @Validated ProductIdParam productIdParam,BindingResult result){
        if (result.hasErrors()){
            return R.fail("商品图片查询失败");
        }
        return productService.pictures(productIdParam);
    }

    @PostMapping("search")
    public R search(@RequestBody ProductSearchParam productSearchParam){
        return productService.search(productSearchParam);
    }
}
