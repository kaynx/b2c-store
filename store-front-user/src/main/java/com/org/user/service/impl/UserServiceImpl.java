package com.org.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.org.constant.UserConstant;
import com.org.param.AddressListParam;
import com.org.param.PageParam;
import com.org.param.UserCheckParam;
import com.org.param.UserLoginParam;
import com.org.pojo.User;
import com.org.user.mapper.UserMapper;
import com.org.user.service.UserService;
import com.org.utils.MD5Util;
import com.org.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 陈晨
 * @date 2023/5/16
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public R check(UserCheckParam userCheckParam) {
        //参数封装
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_name", userCheckParam.getUserName());
        //数据库查询
        Long total = userMapper.selectCount(userQueryWrapper);
        //查询结果处理
        if (total == 0) {
            //数据库中不存在：可用
            log.info("UserServiceImpl.check()业务结束,结果:{}", "账号可以注册");
            return R.ok("账号不存在，可以注册");
        }
        log.info("UserServiceImpl.check()业务结束,结果:{}", "账号不可注册");
        return R.fail("账号已经存在，不可注册");
    }

    @Override
    public R register(User user) {
        //检查账号是否存在
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_name", user.getUserName());
        Long total = userMapper.selectCount(userQueryWrapper);
        if (total > 0) {
            log.info("UserServiceImpl.register()业务结束,结果:{}", "账号存在，注册失败");
            return R.fail("账号已经存在，不可注册");
        }
        //密码加密处理
        String newPwd = MD5Util.encode(user.getPassword() + UserConstant.USER_SALT);
        user.setPassword(newPwd);
        //插入数据库数据
        int rows = userMapper.insert(user);
        //返回封装结果
        if (rows == 0) {
            log.info("UserServiceImpl.register()业务结束,结果:{}", "账号注册失败");
            return R.fail("注册失败，请稍后再试");
        }
        log.info("UserServiceImpl.register()业务结束,结果:{}", "账号注册成功");
        return R.ok("注册成功");
    }

    @Override
    public R login(UserLoginParam userLoginParam) {
        //密码处理
        String newPwd = MD5Util.encode(userLoginParam.getPassword() + UserConstant.USER_SALT);
        //数据路查询
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_name", userLoginParam.getUserName());
        userQueryWrapper.eq("password", newPwd);
        User user = userMapper.selectOne(userQueryWrapper);
        //结果处理
        if (user == null) {
            log.info("UserServiceImpl.login()业务结束,结果:{}", "账号或密码输入错误");
            return R.fail("账号或密码输入错误");
        }
        log.info("UserServiceImpl.login()业务结束,结果:{}", "登录成功");
        return R.ok("登录成功", user);
    }

    @Override
    public R listPage(PageParam pageParam) {
        int pageSize = pageParam.getPageSize();
        int currentPage = pageParam.getCurrentPage();
        IPage<User> page = new Page<>(currentPage, pageSize);
        page = userMapper.selectPage(page, null);
        long total = page.getTotal();
        List<User> records = page.getRecords();
        return R.ok("用户管理查询成功", records, total);


    }

    @Override
    public R remove(AddressListParam addressListParam) {
        int i = userMapper.deleteById(addressListParam.getUserId());
        log.info("UserServiceImpl.remove()业务结束,结果:{}", i);
        return R.ok("用户删除成功");
    }

    @Override
    public R update(User user) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_id", user.getUserId());
        userQueryWrapper.eq("password", user.getPassword());
        Long aLong = userMapper.selectCount(userQueryWrapper);
        if (aLong == 0) {
            user.setPassword(MD5Util.encode(user.getPassword() + UserConstant.USER_SALT));
        }
        int rows = userMapper.updateById(user);
        log.info("UserServiceImpl.update()业务结束,结果:{}", rows);
        return R.ok("用户信息修改成功");

    }

    @Override
    public R save(User user) {
        //检查账号是否存在
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_name", user.getUserName());
        Long total = userMapper.selectCount(userQueryWrapper);
        if (total > 0) {
            log.info("UserServiceImpl.save()业务结束,结果:{}", "账号存在，注册失败");
            return R.fail("账号已经存在，不可注册");
        }
        //密码加密处理
        String newPwd = MD5Util.encode(user.getPassword() + UserConstant.USER_SALT);
        user.setPassword(newPwd);
        //插入数据库数据
        int rows = userMapper.insert(user);
        //返回封装结果
        if (rows == 0) {
            log.info("UserServiceImpl.register()业务结束,结果:{}", "账号添加失败");
            return R.fail("注册失败，请稍后再试");
        }
        log.info("UserServiceImpl.save()业务结束,结果:{}", "账号添加成功");
        return R.ok("添加成功");

    }
}
