package com.org.user.controller;

import com.org.param.AddressListParam;
import com.org.param.PageParam;
import com.org.param.UserCheckParam;
import com.org.param.UserLoginParam;
import com.org.pojo.User;
import com.org.user.service.UserService;
import com.org.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 陈晨
 * @date 2023/5/16
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 检查账号是否可注册
     *
     * @param userCheckParam 要检查的账号实体
     * @param result         获取校验结果的实体
     * @return 封装结果R
     */
    @PostMapping("check")
    public R check(@RequestBody @Validated UserCheckParam userCheckParam, BindingResult result) {
        //检查是否符合校验注解的规则 符合：false 不符合：true
        boolean b = result.hasErrors();

        if (b) {
            return R.fail("账号为null，不可使用");
        }
        return userService.check(userCheckParam);
    }

    @PostMapping("register")
    public R register(@RequestBody @Validated User user, BindingResult result) {
        boolean b = result.hasErrors();
        if (b) {
            return R.fail("参数异常不可注册");
        }
        return userService.register(user);
    }

    @PostMapping("login")
    public R login(@RequestBody @Validated UserLoginParam userLoginParam, BindingResult result) {
        if (result.hasErrors()) {
            return R.fail("参数异常不可登录");
        }
        return userService.login(userLoginParam);
    }

    @PostMapping("admin/list")
    public R listPage(@RequestBody PageParam pageParam) {
        return userService.listPage(pageParam);
    }

    @PostMapping("admin/remove")
    public R remove(@RequestBody AddressListParam addressListParam){
        return userService.remove(addressListParam);
    }

    @PostMapping("admin/update")
    public R update(@RequestBody User user){
        return userService.update(user);
    }

    @PostMapping("admin/save")
    public R save(@RequestBody User user){
        return userService.save(user);
    }
}
