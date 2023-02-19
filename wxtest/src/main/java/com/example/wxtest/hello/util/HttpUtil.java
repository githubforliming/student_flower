package com.example.wxtest.hello.util;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import sun.misc.BASE64Encoder;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 还是那句话 这里简单实现 不做各种校验 和 优化
 * 主要看公众号登录实现逻辑
 */
public class HttpUtil {

  /**
   * get   获取图片
   */
  public static String doGet(String url){
    try {
      HttpClient httpClient = new DefaultHttpClient();
      HttpGet request = new HttpGet(url);
      HttpResponse execute = httpClient.execute(request);
      // 返回
      HttpEntity entity = execute.getEntity();
      // 读取
      InputStream inputStream = entity.getContent();
      ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
      int ch;
      try {
        while ((ch = inputStream.read()) != -1) {
          bytestream.write(ch);
        }
      }
      catch (IOException e) {
        e.printStackTrace();
      }
      byte[] bytes = bytestream.toByteArray();
      String base64EncoderImg = Base64.encodeBase64String(bytes);
      return "data:image/png;base64,"+base64EncoderImg;
    } catch (Exception e) {
      System.out.println("获取二维码错了！！");
    }
    return null;
  }

  /**
   * post
   */
  public static String doPost(String url , JSONObject body){
    try {
      HttpPost request = new HttpPost(url);
      request.addHeader("Content-type", "application/json; charset=utf-8");
      request.setHeader("Accept", "application/json");

      HttpClient httpClient = new DefaultHttpClient();
      if (body != null) {
        System.out.println("发送："+body);
         request.setEntity(new StringEntity(body.toString()));
      }

      HttpResponse execute = httpClient.execute(request);
      // 返回
      HttpEntity entity = execute.getEntity();
      String json = EntityUtils.toString(entity);
      System.out.println(json);
      return json;
    } catch (Exception e ) {
      System.out.println("获取ticket错了！！！");
    }
    return null;
  }
}

