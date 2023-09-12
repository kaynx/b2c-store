package com.org.admin.controller;

import com.org.admin.param.AdminUserParam;
import com.org.admin.pojo.AdminUser;
import com.org.admin.service.AdminUserService;
import com.org.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @author 陈晨
 * @date 2023/5/30
 */
@Slf4j
@RestController
@RequestMapping("user")
public class AdminUserController {
    @Autowired
    private AdminUserService adminUserService;

    @PostMapping("login")
    public R login(@Validated AdminUserParam adminUserParam, BindingResult result, HttpSession session) {
        //参数校验
        if (result.hasErrors()) {
            log.info("AdminUserController.login业务结束，结果:{}","参数异常");
        }
        //验证码校验
        String captcha = (String) session.getAttribute("captcha");
        if (!adminUserParam.getVerCode().equalsIgnoreCase(captcha)) {
            return R.fail("登录失败,验证码错误!");
        }

        AdminUser adminUser = adminUserService.login(adminUserParam);
        if (adminUser == null) {
            return R.fail("登录失败,账号或者密码错误!");
        }
        session.setAttribute("userInfo", adminUser);
        return R.ok("登录成功");
    }
    @GetMapping("logout")
    public R logout(HttpSession session){
        session.invalidate();
        return R.ok("退出登录成功");
    }

}
