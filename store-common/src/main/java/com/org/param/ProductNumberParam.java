package com.org.param;

import lombok.Data;

import java.io.Serializable;


/**
 * @author 陈晨
 * @date 2023/5/29
 */
@Data
public class ProductNumberParam implements Serializable {
    public static final Long serialVersionUID = 1L;
    //商品id
    private Integer productId;
    //购买数量
    private Integer num;
}
