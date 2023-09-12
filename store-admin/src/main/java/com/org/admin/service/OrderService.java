package com.org.admin.service;

import com.org.param.PageParam;
import com.org.utils.R;

/**
 * @author 陈晨
 * @date 2023/6/16
 */
public interface OrderService {
    /**
     * 查询订单数据
     *
     * @param pageParam
     * @return
     */
    R list(PageParam pageParam);
}
