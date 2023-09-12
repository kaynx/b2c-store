package com.org.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.org.param.OrderParam;
import com.org.param.PageParam;
import com.org.pojo.Order;
import com.org.utils.R;

/**
 * @author 陈晨
 * @date 2023/5/29
 */
public interface OrderService extends IService<Order> {
    /**
     * 保存订单
     *
     * @param orderParam
     * @return
     */
    R save(OrderParam orderParam);

    /**
     * 订单数据查询
     *
     * @param orderParam
     * @return
     */
    R list(OrderParam orderParam);

    /**
     * 检查订单中是否有商品被引用
     *
     * @param productId
     * @return
     */
    R check(Integer productId);

    /**
     * 后台管理查询订单数据
     *
     * @param pageParam
     * @return
     */
    R adminList(PageParam pageParam);
}
