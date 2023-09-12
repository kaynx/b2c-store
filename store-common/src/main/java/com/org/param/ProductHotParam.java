package com.org.param;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author 陈晨
 * @date 2023/5/19
 */
@Data
public class ProductHotParam {
    @NotEmpty
    private List<String> categoryName;
}
