package com.org.param;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author 陈晨
 * @date 2023/5/18
 */
@Data
public class ProductPromoParam {
    @NotBlank
    private String categoryName;
}
