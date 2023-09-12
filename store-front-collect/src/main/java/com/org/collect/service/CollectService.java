package com.org.collect.service;

import com.org.pojo.Collect;
import com.org.utils.R;

/**
 * @author 陈晨
 * @date 2023/5/26
 */
public interface CollectService {
    /**
     * 添加收藏
     *
     * @param collect
     * @return
     */
    R save(Collect collect);

    /**
     * 根据用户id查询商品集合
     *
     * @param userId
     * @return
     */
    R list(Integer userId);

    /**
     * 根据用户id和商品id删除收藏数据
     *
     * @param collect
     * @return
     */
    R remove(Collect collect);

    /**
     * 根据商品id删除
     *
     * @param productId
     * @return
     */
    R removeByPid(Integer productId);
}
