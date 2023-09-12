package com.org.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.org.pojo.Address;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author 陈晨
 * @date 2023/5/29
 */
@Data
public class AddressParam {
    @NotNull
    @JsonProperty("user_id")
    private Integer userId;

    private Address add;

}
