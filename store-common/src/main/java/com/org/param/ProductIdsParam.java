package com.org.param;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 陈晨
 * @date 2023/5/19
 */
@Data
public class ProductIdsParam extends PageParam{
    @NotNull
    private List<Integer> categoryID;
}
