package com.org.param;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author 陈晨
 * @date 2023/5/21
 */
@Data
public class ProductIdParam {
    @NotNull
    private Integer productID;
}
