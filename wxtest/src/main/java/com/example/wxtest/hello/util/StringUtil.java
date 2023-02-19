package com.example.wxtest.hello.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * @author 木子的昼夜编程
 */
public class StringUtil {
  // 输入流转字符串
  public static String getStringByInputStream(InputStream inputStream){
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    try {
      byte[] b = new byte[10240];
      int n;
      while ((n = inputStream.read(b)) != -1) {
        outputStream.write(b, 0, n);
      }
    } catch (Exception e) {
      try {
        inputStream.close();
        outputStream.close();
      } catch (Exception e1) {
      }
    }
    return outputStream.toString();
  }
}
