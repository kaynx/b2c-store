package com.org.user.service;

import com.org.pojo.Address;
import com.org.utils.R;

/**
 * @author 陈晨
 * @date 2023/5/17
 */
public interface AddressService {
    /**
     * 根据用户id查询地址数据
     * @param userId
     * @return 001 004
     */
    R list(Integer userId);

    /**
     * 插入地址数据
     * @param address
     * @return
     */
    R save(Address address);

    /**
     * 根据id删除用户地址数据
     * @param id
     * @return
     */
    R remove(Integer id);
}
