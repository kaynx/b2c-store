package com.org.admin.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 陈晨
 * @date 2023/5/31
 */
@Component
@Slf4j
public class LoginProtectInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object userInfo = request.getSession().getAttribute("userInfo");
        log.info("LoginProtectInterceptor.preHandle业务结束，结果:{}", userInfo);
        if (userInfo == null) {
            //重定向到登录页面
            response.sendRedirect(request.getContextPath() + "/index.html");
            return false;
        } else {
            //放行
            return true;
        }
    }
}
