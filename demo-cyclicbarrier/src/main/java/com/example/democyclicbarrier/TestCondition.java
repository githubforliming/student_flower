package com.example.democyclicbarrier;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程
 * 一个生活在互联网底层，做着增删改查的码农,不谙世事的造作
 * @create 2021-08-15 15:36
 */
public class TestCondition {
    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        lock.lock();
        long nanos = TimeUnit.SECONDS.toNanos(5);
        long lo = condition.awaitNanos(nanos);
        System.out.println(lo);

        lock.unlock();

        CountDownLatch
    }
}
