package com.org.admin.controller;

import com.org.admin.service.UserService;
import com.org.param.AddressListParam;
import com.org.param.PageParam;
import com.org.pojo.User;
import com.org.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 陈晨
 * @date 2023/5/31
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("list")
    public R listPage(PageParam pageParam) {
        return userService.listPage(pageParam);
    }

    @PostMapping("remove")
    public R removeUser(AddressListParam addressListParam){
        return userService.removeUser(addressListParam);
    }

    @PostMapping("update")
    public R update(User user){
        return userService.updateUser(user);
    }

    @PostMapping("save")
    public R saveUser(User user){
        return userService.saveUser(user);
    }


}
