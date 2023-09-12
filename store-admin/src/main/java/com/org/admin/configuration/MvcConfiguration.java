package com.org.admin.configuration;

import com.org.admin.interceptor.LoginProtectInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 陈晨
 * @date 2023/5/31
 */
@Configuration
public class MvcConfiguration implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截后台管理模块的路径  排除登录和资源路径
        registry.addInterceptor(new LoginProtectInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/",
                        "/index.html",
                        "/index",
                        "/static/**",
                        "/user/login",
                        "/user/logout",
                        "/api/**",
                        "/css/**",
                        "/images/**",
                        "/js/**",
                        "/lib/**",
                        "/captcha");
    }
}
