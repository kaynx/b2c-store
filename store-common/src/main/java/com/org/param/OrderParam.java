package com.org.param;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.org.vo.CartVo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 陈晨
 * @date 2023/5/29
 */
//订单参数
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class OrderParam implements Serializable {

    public static final Long serialVersionUID = 1L;

    @JsonProperty("user_id")
    private Integer userId;
    private List<CartVo> products;

}




