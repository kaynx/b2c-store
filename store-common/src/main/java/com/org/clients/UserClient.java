package com.org.clients;

import com.org.param.AddressListParam;
import com.org.param.PageParam;
import com.org.pojo.User;
import com.org.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 陈晨
 * @date 2023/5/31
 */
@FeignClient("user-service")
public interface UserClient {
    /**
     * 后台管理调用，查询全部用户数据
     *
     * @param pageParam
     * @return
     */
    @PostMapping("user/admin/list")
    R listPage(@RequestBody PageParam pageParam);

    /**
     * 根据用户id删除用户
     *
     * @param addressListParam
     * @return
     */
    @PostMapping("user/admin/remove")
    R removeUser(@RequestBody AddressListParam addressListParam);

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    @PostMapping("user/admin/update")
    R update(@RequestBody User user);

    /**
     * 添加用户信息
     *
     * @param user
     * @return
     */
    @PostMapping("user/admin/save")
     R save(@RequestBody User user);
}
