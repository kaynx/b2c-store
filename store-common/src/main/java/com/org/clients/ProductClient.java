package com.org.clients;

import com.org.param.ProductCollectParam;
import com.org.param.ProductIdParam;
import com.org.param.ProductSaveParam;
import com.org.pojo.Product;
import com.org.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author 陈晨
 * @date 2023/5/21
 */
@FeignClient("product-service")
public interface ProductClient {
    /**
     * 搜索服务调用，获取全部商品数据
     * 进行同步
     *
     * @return
     */
    @GetMapping("/product/list")
    List<Product> list();

    @PostMapping("product/collect/list")
    R productIds(@RequestBody @Validated ProductCollectParam productCollectParam);

    @PostMapping("product/cart/detail")
    Product cartDetail(@RequestBody @Validated ProductIdParam productIdParam);

    @PostMapping("product/cart/list")
    List<Product> list(@RequestBody @Validated ProductCollectParam productCollectParam);

    @PostMapping("product/admin/count")
    Long adminCount(@RequestBody Integer categoryId);

    @PostMapping("product/admin/save")
    R adminSave(@RequestBody ProductSaveParam productParam);

    @PostMapping("product/admin/update")
    R adminUpdate(@RequestBody Product product);

    @PostMapping("product/admin/remove")
    R adminRemove(@RequestBody Integer productId);
}
