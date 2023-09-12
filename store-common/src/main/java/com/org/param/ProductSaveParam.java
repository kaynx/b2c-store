package com.org.param;

import com.org.pojo.Product;
import lombok.Data;

/**
 * @author 陈晨
 * @date 2023/6/4
 */
@Data
public class ProductSaveParam extends Product {
    private String pictures;
}
