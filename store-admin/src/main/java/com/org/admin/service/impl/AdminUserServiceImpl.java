package com.org.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.org.admin.mapper.AdminUserMapper;
import com.org.admin.param.AdminUserParam;
import com.org.admin.pojo.AdminUser;
import com.org.admin.service.AdminUserService;
import com.org.constant.UserConstant;
import com.org.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 陈晨
 * @date 2023/5/30
 */
@Service
@Slf4j
public class AdminUserServiceImpl implements AdminUserService {
    @Autowired
    private AdminUserMapper adminUserMapper;

    @Override
    public AdminUser login(AdminUserParam adminUserParam) {
        QueryWrapper<AdminUser> adminUserQueryWrapper = new QueryWrapper<>();
        adminUserQueryWrapper.eq("user_account", adminUserParam.getUserAccount());
        adminUserQueryWrapper.eq("user_password", MD5Util.encode(adminUserParam.getUserPassword() + UserConstant.USER_SALT));
        AdminUser adminUser = adminUserMapper.selectOne(adminUserQueryWrapper);
        log.info("AdminUserServiceImpl.login业务结束，结果:{}", adminUser);
        return adminUser;
    }
}
