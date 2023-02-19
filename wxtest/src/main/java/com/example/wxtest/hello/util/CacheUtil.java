package com.example.wxtest.hello.util;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.TimeUnit;

/**
 * @author 木子的昼夜编程
 */
public class CacheUtil {
  public static Cache<String,String> cache = CacheBuilder.newBuilder()
          // 缓存写入后 30分钟过期
          .expireAfterWrite(30*60, TimeUnit.SECONDS)
          .build();


  // 添加缓存
  public static void addCache(String key, String value) {
    cache.put(key,value);
  }

  // 判断是否存在
  public static boolean exist(String key) {
    return cache.asMap().containsKey(key);
  }
}
