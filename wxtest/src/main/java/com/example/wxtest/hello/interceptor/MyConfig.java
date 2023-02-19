package com.example.wxtest.hello.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author 木子的昼夜编程
 *
 * 配置  添加需要拦截的地址 和 不需要拦截的地址
 */
@Configuration
public class MyConfig extends WebMvcConfigurationSupport {

  @Autowired
  MyInterceptor myInterceptor;

  // 注册拦截器
  @Override
  protected void addInterceptors(InterceptorRegistry registry) {
//    super.addInterceptors(registry);
    registry.addInterceptor(myInterceptor)
            .addPathPatterns("/**")
            // page/xxx不用权限校验
            .excludePathPatterns("/page/**")

            // 微信回调不用权限校验
            .excludePathPatterns("/wx/auth")

            // 获取二维码接口不用权限校验
            // 校验是否登录成功接口不用权限校验
            .excludePathPatterns("/wx/nologin/**");
  }

  /**
   * 添加静态资源文件，外部可以直接访问地址
   * @param registry
   */
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //需要配置1：----------- 需要告知系统，这是要被当成静态文件的！
    //第一个方法设置访问路径前缀，第二个方法设置资源路径
    registry.addResourceHandler("/page/**").addResourceLocations("classpath:/static/page/");
  }
}
