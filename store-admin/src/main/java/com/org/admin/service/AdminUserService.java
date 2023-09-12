package com.org.admin.service;

import com.org.admin.param.AdminUserParam;
import com.org.admin.pojo.AdminUser;

/**
 * @author 陈晨
 * @date 2023/5/30
 */
public interface AdminUserService {
    /**
     * 后台登录业务
     * @param adminUserParam
     * @return
     */
    AdminUser login(AdminUserParam adminUserParam);
}
