package com.org.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 陈晨
 * @date 2023/5/29
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@TableName("orders")
public class Order implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    @JsonProperty("order_id")
    private Long orderId;
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("product_id")
    private Integer productId;
    @JsonProperty("product_num")
    private Integer productNum;
    @JsonProperty("product_price")
    private Double productPrice;
    @JsonProperty("order_time")
    private Long orderTime;
}

