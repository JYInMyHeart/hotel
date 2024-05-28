package com.alex.conf;

import com.alex.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Resource
    private LoginInterceptor myInterceptor;


    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        //addPathPatterns("/**")  拦截的路径;    excludePathPatterns("/login")  不拦截的路径
        registry.addInterceptor(myInterceptor).addPathPatterns("/**").excludePathPatterns(
                "/user/login",
                "/user/excel",
                "/user/list",
                "/user/update",
                "/user/add",
                "/user/selectUserByIdentityID",
                "/notice/list",
                "/floor/list",
                "/picture/list",
                "/declare/list",
                "/declare/listByKeyword",
                "/introduce/list",
                "/room/list",
                "/user/uploadUser",
                "/user/upload",
                "/static/**",
                "/swagger-ui.html",
                "/webjars/**");
    }


}
