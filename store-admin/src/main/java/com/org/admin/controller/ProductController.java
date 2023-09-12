package com.org.admin.controller;

import com.org.admin.service.ProductService;
import com.org.admin.utils.AliyunOssUtils;
import com.org.param.ProductSaveParam;
import com.org.param.ProductSearchParam;
import com.org.pojo.Product;
import com.org.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @author 陈晨
 * @date 2023/6/3
 */
@Slf4j
@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private AliyunOssUtils aliyunOssUtils;

    @GetMapping("list")
    public R search(ProductSearchParam productSearchParam) {
        return productService.search(productSearchParam);
    }

    @PostMapping("upload")
    public R upload(@RequestParam("img") MultipartFile img) throws Exception {
        String filename = img.getOriginalFilename();
        filename += UUID.randomUUID().toString().replaceAll("-", "");
        String contentType = img.getContentType();
        byte[] bytes = img.getBytes();
        int hours = 1000;
        String uploadImage = aliyunOssUtils.uploadImage(filename, bytes, contentType, hours);
        log.info("url:{}", uploadImage);
        return R.ok("图片上传成功", uploadImage);
    }

    @PostMapping("save")
    public R adminSave(ProductSaveParam productSaveParam) {
        return productService.save(productSaveParam);
    }

    @PostMapping("update")
    public R adminUpdate(Product product) {
        return productService.update(product);
    }
    @PostMapping("remove")
    public R adminRemove(Integer productId) {
        return productService.remove(productId);
    }

}
