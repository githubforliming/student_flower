package com.example.springbootdemo.handler;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程
 * 一个生活在互联网底层，做着增删改查的码农,不谙世事的造作
 * @create 2021-08-21 16:27
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
@Configuration
public class TestConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    HandlerInterceptorTest handlerInterceptorTest;
    /**
     * 注册拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(handlerInterceptorTest).addPathPatterns("/**")
                .excludePathPatterns("/login")
                .excludePathPatterns("/getLogin");
    }
}
