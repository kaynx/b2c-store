package com.org.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.org.pojo.Cart;
import com.org.pojo.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 陈晨
 * @date 2023/5/28
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class CartVo implements Serializable {
    //购物车id
    private Integer id;
    //商品id
    private Integer productID;
    //商品名称
    private String productName;
    //商品显示图片
    private String productImg;
    //商城价格
    private Double price;
    //商品购买数量
    private Integer num;
    //商品限购数量
    private Integer maxNum;
    //是否勾选
    private Boolean check = false;

    public CartVo(Product product, Cart cart) {
        this.id = cart.getId();
        this.productID = product.getProductId();
        this.productName = product.getProductName();
        this.productImg = product.getProductPicture();
        this.price = product.getProductSellingPrice();
        this.num = cart.getNum();
        this.maxNum = product.getProductNum();
        this.check = false;
    }
}
