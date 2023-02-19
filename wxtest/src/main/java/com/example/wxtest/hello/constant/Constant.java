package com.example.wxtest.hello.constant;

/**
 * @author 木子的昼夜编程
 */
public class Constant {
  // 获取ticket
  public static String  QRCODECREATEURL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=%s";
  // ticket换取 二维码
  public static String  SHOWQRCODE = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=%s";
}
