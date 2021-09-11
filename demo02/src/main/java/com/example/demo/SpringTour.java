package com.example.demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程
 * 一个生活在互联网底层，做着增删改查的码农,不谙世事的造作
 * @create 2021-04-18 10:36
 */
public class SpringTour {
    //
    static CountDownLatch c = new CountDownLatch(3);
    public static void main(String[] args) {
        Semaphore
        // 小强的活动
        new Thread(()->{
            try {
                System.out.println("小强来了 坐那等着其他人");
                c.await();
                System.out.println("小强发车 目标:邯郸");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"小强").start();

        // 其他三个人活动
        new Thread(()->{
            try {
                String[] ps = {"小明","小月月","小月鸟"};
                for (int i = 0; ps!=null && i < ps.length; i++) {
                    Thread.sleep(3000);
                    System.out.println(ps[i]+" 到了！");
                    c.countDown();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"其他三个人").start();
    }
}
