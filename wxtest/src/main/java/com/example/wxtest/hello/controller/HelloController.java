package com.example.wxtest.hello.controller;

import com.alibaba.fastjson2.JSONObject;
import com.example.wxtest.hello.constant.Constant;
import com.example.wxtest.hello.entity.CheckLoginEntity;
import com.example.wxtest.hello.entity.Demo;
import com.example.wxtest.hello.entity.QrEntity;
import com.example.wxtest.hello.util.CacheUtil;
import com.example.wxtest.hello.util.HttpUtil;
import com.example.wxtest.hello.util.StringUtil;
import com.example.wxtest.hello.util.XmlUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Random;

/**
 * @author 木子的昼夜编程
 */
@RestController
@RequestMapping("/wx")
public class HelloController {


  // 填写服务器地址的时候 需要验证token 这里我们就简单直接返回echostr 不做校验
  @RequestMapping("/auth")
  public String  hello(@RequestParam(name = "signature", required = false) String signature,
                       @RequestParam(name = "timestamp", required = false) String timestamp,
                       @RequestParam(name = "nonce", required = false) String nonce,
                       @RequestParam(name = "echostr", required = false) String echostr){
    System.out.println(String.format("接收到来自微信服务器的认证消息：[%s, %s, %s, %s]", signature,
            timestamp, nonce, echostr));
    // 经过一系列校验 我们就不校验了。为了方便
    return echostr;
  }

  // 所有事件 都会回调这里  包括：公众号关注事件等
  @PostMapping("/auth")
  public void   hello(HttpServletRequest request) throws IOException {
    String str = StringUtil.getStringByInputStream(request.getInputStream());
    // 1. 解析xml
    JSONObject jsonObject = XmlUtil.parsXml(str);
    String event = jsonObject.getString("Event");
    String msgType = jsonObject.getString("MsgType");
    String fromUserName = jsonObject.getString("FromUserName");
    // 扫描关注
    if ("event".equals(msgType)) {
      if ("subscribe".equals(event) || "SCAN".equals(event)) {
        System.out.println("登录啦！！");
        String ticket = jsonObject.getString("Ticket");
        CacheUtil.addCache(DigestUtils.md5Hex(ticket), ticket);
      }
    }
    // 这里该一般需要记录fromUserName 我们就不记录了 我们就能登录即可

  }

  // 测试获取数据
  @PostMapping("/getdata")
  public String   hello(@RequestBody Demo demo, HttpServletRequest request) throws IOException {
    System.out.println(String.format("参数:%s", demo));
    return "获取到数据了！！！";
  }

  // 测试获取数据
  @PostMapping("/nologin/getqrcode")
  public QrEntity getqrcode(HttpServletRequest request) throws IOException {
    QrEntity qrEntity = new QrEntity();
    // 1. 生成scene_id
    int scene_id = new Random().nextInt();
    // 2. 获取access_token
    // GET https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
    // 由于我们没地儿存储这个access_token 因为需要一直重启项目 内存会消失
    // 所以就直接通过地址栏访问一下 获取  然后写死在这里
    // 正式环境上肯定是 获取到了 放到redis 设置过期时间 过期了重新获取
    // 直接替换APPID和APPSECRET 浏览器地址栏访问 就能获取到
    // 有人问为啥不每次获取呢  微信是有次数限制的  万一掉过头了那不尴尬了
    String accesToken ="65_2pSNRhYbucIL6Bkqjh5EtOXKHllI4OTTIejlxcmS-l6uitikuMMt2cKQOCgDhzFN6W9xby5aew_vn7rhu5f-yajCHnigLGisKvzO7wZZvZHXOKOi-Dza9f3ioegGTNfABAWJD";
    // 2. 请求微信 获取ticket
    JSONObject params =  new JSONObject();
    // {"expire_seconds": 604800, "action_name": "QR_SCENE", "action_info": {"scene": {"scene_id": 123}}}
    params.put("expire_seconds", "604800");
    params.put("action_name","QR_SCENE");
    // 参数
    JSONObject action_info = new JSONObject();
    JSONObject scene = new JSONObject();
    scene.put("scene_id", scene_id);
    action_info.put("scene", scene);
    params.put("action_info",action_info.toJSONString());

    String res = HttpUtil.doPost(String.format(Constant.QRCODECREATEURL, accesToken), params);
    if (StringUtils.isNoneEmpty(res)) {
      JSONObject resObj = JSONObject.parseObject(res);
      String ticket = resObj.getString("ticket");
      if (ticket!=null) {
        // 3. ticket换取二维码
        String img = HttpUtil.doGet(String.format(Constant.SHOWQRCODE, ticket));
        qrEntity.setTicket(ticket);
        qrEntity.setImg(img);
      }
    }
    return qrEntity;
  }

  // 测试获取数据
  @PostMapping("/nologin/checklogin/")
  public String getqrcode(@RequestBody CheckLoginEntity entity) {
    String token = DigestUtils.md5Hex(entity.getTicket());
    //
    if (CacheUtil.exist(token)) {
      return token;
    }
    return null;
  }
}
