package com.org.param;

import lombok.Data;

/**
 * @author 陈晨
 * @date 2023/5/21
 */
@Data
public class PageParam {
    private int currentPage = 1;
    private int pageSize = 15;
}
