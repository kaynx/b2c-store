package com.org.param;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author 陈晨
 * @date 2023/5/28
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartParam {

    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("product_id")
    private Integer productId;
    private Integer num;
}

