package com.example.springbootdemo.handler;

import org.springframework.stereotype.Service;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程
 * 一个生活在互联网底层，做着增删改查的码农,不谙世事的造作
 * @create 2021-08-21 16:06
 */
@Service
public class TestServiceImpl implements  TestService{

    @Override
    public void say() {
        System.out.println("say what");
    }
}
