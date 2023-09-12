package com.org.admin.service;

import com.org.param.AddressListParam;
import com.org.param.PageParam;
import com.org.pojo.User;
import com.org.utils.R;

/**
 * @author 陈晨
 * @date 2023/5/31
 */
public interface UserService {
    /**
     * 用户展示
     *
     * @param pageParam
     * @return
     */
    R listPage(PageParam pageParam);

    /**
     * 根据用户id删除用户
     *
     * @param addressListParam
     * @return
     */
    R removeUser(AddressListParam addressListParam);

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    R updateUser(User user);

    /**
     * 添加用户信息
     *
     * @param user
     * @return
     */
    R saveUser(User user);
}
