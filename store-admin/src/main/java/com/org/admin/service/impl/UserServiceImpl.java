package com.org.admin.service.impl;

import com.org.admin.service.UserService;
import com.org.clients.UserClient;
import com.org.param.AddressListParam;
import com.org.param.PageParam;
import com.org.pojo.User;
import com.org.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author 陈晨
 * @date 2023/5/31
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserClient userClient;

    @Cacheable(value = "list.user", key = "#pageParam.pageSize+'-'+#pageParam.currentPage")
    @Override
    public R listPage(PageParam pageParam) {
        log.info("UserServiceImpl.pageParam业务开始，结果:{}", pageParam);
        R ok = userClient.listPage(pageParam);
        log.info("UserServiceImpl.listPage业务结束，结果:{}", ok);
        return ok;
    }

    @CacheEvict(value = "list.user", allEntries = true)
    @Override
    public R removeUser(AddressListParam addressListParam) {
        R ok = userClient.removeUser(addressListParam);
        log.info("UserServiceImpl.removeUser业务结束，结果:{}", ok);
        return ok;
    }

    @CacheEvict(value = "list.user", allEntries = true)
    @Override
    public R updateUser(User user) {
        R ok = userClient.update(user);
        log.info("UserServiceImpl.updateUser业务结束，结果:{}", ok);
        return ok;
    }

    @CacheEvict(value = "list.user", allEntries = true)
    @Override
    public R saveUser(User user) {
        R ok = userClient.save(user);
        log.info("UserServiceImpl.saveUser业务结束，结果:{}", ok);
        return ok;
    }
}
