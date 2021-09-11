package com.example.democyclicbarrier;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程
 * 一个生活在互联网底层，做着增删改查的码农,不谙世事的造作
 * @create 2021-08-15 10:07
 */
public class TestCyc {
    public static void main(String[] args) {
        System.out.println("小强准备赚钱...");
        System.out.println("测试小强力气...");
        // 重点关注代码
        CyclicBarrier barr = new CyclicBarrier(3, ()->{
            try {
                System.out.println("够三块了，小强吭哧吭哧搬到车上去...");
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println("小强不干了...");
            }
        });
        System.out.println("测试结果：一次能搬"+barr.getParties()+"块");
        System.out.println("开始搬砖...");
        // 假设一天搬5次
        new Thread(()->{
            for (int i = 0; i < 5 ; i++) {
                try{
                    System.out.println("A放一块砖到小强手里.");
                    barr.await();
                    // 够三块了 才会执行下边代码逻辑
                } catch (Exception e) {
                    System.out.println("A不干了");
                }
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i < 5 ; i++) {
                try {
                    System.out.println("B放一块砖到小强手里.");
                    barr.await();
                    // 够三块了 才会执行下边代码逻辑
                } catch (Exception e) {
                    System.out.println("B不干了");
                }
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i < 5 ; i++) {
                try {
                    System.out.println("C放一块砖到小强手里.");
                    barr.await();
                    // 够三块了 才会执行下边代码逻辑
                } catch (Exception e) {
                    System.out.println("C不干了");
                }
            }
        }).start();
    }
}
