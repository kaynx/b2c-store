package com.org.doc;

import com.org.pojo.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 陈晨
 * @date 2023/5/21
 */
@Data
@NoArgsConstructor
public class ProductDoc extends Product {
    /**
     * 商品名称和商品描述的综合值
     */
    private String all;

    public ProductDoc(Product product) {
        super(product.getProductId(), product.getProductName(), product.getCategoryId(), product.getProductTitle(), product.getProductIntro(), product.getProductPicture(), product.getProductPrice(), product.getProductSellingPrice(), product.getProductNum(), product.getProductSales());
        this.all = product.getProductName() + product.getProductTitle() + product.getProductTitle();
    }
}
