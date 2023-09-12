package com.org.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author 陈晨
 * @date 2023/5/17
 */
@Data
public class AddressRemoveParam {
    @NotNull
    private Integer id;
}
