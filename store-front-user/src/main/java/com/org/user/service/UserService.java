package com.org.user.service;

import com.org.param.AddressListParam;
import com.org.param.PageParam;
import com.org.param.UserCheckParam;
import com.org.param.UserLoginParam;
import com.org.pojo.User;
import com.org.utils.R;

/**
 * @author 陈晨
 * @date 2023/5/16
 */
public interface UserService {
    /**
     * 检查账号是否可以注册
     *
     * @param userCheckParam
     * @return 001 004
     */
    R check(UserCheckParam userCheckParam);

    /**
     * 注册业务
     *
     * @param user
     * @return 001 004
     */
    R register(User user);

    /**
     * 登录业务
     *
     * @param userLoginParam 账号和密码
     * @return 001 004
     */
    R login(UserLoginParam userLoginParam);

    /**
     * 后台管理调用，查询全部用户数据
     *
     * @param pageParam
     * @return
     */
    R listPage(PageParam pageParam);

    /**
     * 后台管理：根据用户id删除用户
     *
     * @param addressListParam
     * @return
     */
    R remove(AddressListParam addressListParam);

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    R update(User user);

    /**
     * 添加用户信息
     *
     * @param user
     * @return
     */
    R save(User user);
}
