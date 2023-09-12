package com.org.param;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 陈晨
 * @date 2023/5/30
 */
@Data
public class OrderProductParam {
    //商品id
    private Integer productId;
    //购买数量
    private Integer productNum;
}
