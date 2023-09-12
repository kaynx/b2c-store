package com.org.clients;

import com.org.param.ProductSearchParam;
import com.org.pojo.Product;
import com.org.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

/**
 * @author 陈晨
 * @date 2023/5/22
 */
@FeignClient("search-service")
public interface SearchClient {
    @PostMapping("search/product")
    R searchProduct(@RequestBody ProductSearchParam productSearchParam);

    @PostMapping("search/save")
    R saveOrUpdate(@RequestBody Product product);

    @PostMapping("search/remove")
    R removeProduct(@RequestBody Integer productId);
}
