package com.example.wxtest.hello.interceptor;

import com.example.wxtest.hello.util.CacheUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author 木子的昼夜编程
 * 拦截  token校验
 */
@Component
public class MyInterceptor implements HandlerInterceptor {

  //定义拦截器
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    String mytoken = request.getHeader("mytoken");
    System.out.println(String.format("登录校验mytoken=%s", mytoken));
    // 如果缓存不存在 抛出异常
    if (mytoken == null || !CacheUtil.exist(mytoken)) {
      System.out.println("未登录");
      returnNoLogin(response);
      return false;
    }
      return true;
  }

  /**
   * 返回未登录的错误信息
   * @param response ServletResponse
   */
  private void returnNoLogin(HttpServletResponse response) throws IOException {
    ServletOutputStream outputStream = response.getOutputStream();
    // 设置返回401 和响应编码
    response.setStatus(401);
    outputStream.write("未登录".getBytes(StandardCharsets.UTF_8));
  }
}
