package com.example.democyclicbarrier;
import java.util.concurrent.CyclicBarrier;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程
 * 一个生活在互联网底层，做着增删改查的码农,不谙世事的造作
 * @create 2021-08-15 10:07
 */
public class TestCyc02 {
    public static void main(String[] args) throws Exception {
        // 重点关注代码
        CyclicBarrier barr = new CyclicBarrier(2, ()->{
            try {
                Thread.sleep(10000);
                System.out.println("到达栅栏数啦~~");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        new Thread(()->{
            try{
                System.out.println("一代...");
                barr.await();
            } catch (Exception e) {
               e.printStackTrace();
            }
        }).start();
        new Thread(()->{
            try{
                System.out.println("一代...");
                barr.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(1000);
        Thread thread = new Thread(() -> {
            try {
                System.out.println("二代...");
                barr.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
        thread.interrupt();

        barr.reset();
    }
}
