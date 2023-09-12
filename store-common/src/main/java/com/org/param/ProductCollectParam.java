package com.org.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 陈晨
 * @date 2023/5/26
 */
@Data
public class ProductCollectParam {
    @NotEmpty
    private List<Integer> productIds;
}
