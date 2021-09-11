package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程
 * 一个生活在互联网底层，做着增删改查的码农,不谙世事的造作
 * @create 2021-04-17 19:09
 */
@RestController
public class Cc {
    @RequestMapping
    public String s (){
        System.out.println("=====================");
        return "==222211尔尔111";
    }
}
