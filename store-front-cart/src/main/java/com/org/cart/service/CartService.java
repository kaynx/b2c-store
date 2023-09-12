package com.org.cart.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.org.cart.mapper.CartMapper;
import com.org.param.CartParam;
import com.org.pojo.Cart;
import com.org.utils.R;

import java.util.List;

/**
 * @author 陈晨
 * @date 2023/5/28
 */
public interface CartService {
    /**
     * 添加购物车
     *
     * @param cartParam
     * @return
     */
    R save(CartParam cartParam);

    /**
     * 购物车展示
     *
     * @param cartParam
     * @return
     */
    R list(CartParam cartParam);

    /**
     * 购物车修改
     *
     * @param cartParam
     * @return
     */
    R update(CartParam cartParam);

    /**
     * 购物车删除
     *
     * @param cartParam
     * @return
     */
    R remove(CartParam cartParam);

    /**
     * 清空对应id的购物车项
     *
     * @param cartIds
     */
    void removeBatchByIds(List<Integer> cartIds);

    /**
     * 查询购物车项
     *
     * @param productId
     * @return
     */
    R check(Integer productId);
}
