package com.org.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 陈晨
 * @date 2023/6/16
 */
@Data
public class AdminOrderVo implements Serializable {
    public static final Long serialVersionUID = 1L;
    @JsonProperty("oder_id")
    private Long oderId;
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("product_num")
    private Integer productNum;
    @JsonProperty("order_num")
    private Integer orderNum;
    @JsonProperty("order_price")
    private Double orderPrice;
    @JsonProperty("order_time")
    private Long orderTime;
}
