package test;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程
 * 一个生活在互联网底层，做着增删改查的码农,不谙世事的造作
 * @create 2021-08-21 17:06
 */

public class PhaserTest {
    public static void main(String[] args) {
        Phaser phaser = new MyPhaser();
        // 到5个人就进行下一个步骤
        phaser.bulkRegister(5);
        // 开始执行
        new Thread(new Person("小月月", phaser)).start();
        new Thread(new Person("小强", phaser)).start();
        new Thread(new Person("小明", phaser)).start();
        new Thread(new Person("新人小月鸟", phaser)).start();
        new Thread(new Person("新人新娘", phaser)).start();
    }
}

class MyPhaser extends Phaser {
    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        switch (phase) {
            case 0:
                System.out.println("大家都到了!,参与人数："+registeredParties);
                System.out.println();
                // 返回false 接着执行 返回true结束
                return false;
            case 1:
                System.out.println("大家一起吃饭,参与人数："+registeredParties);
                System.out.println();
                // 返回false 接着执行 返回true结束
                return false;
            case 2:
                System.out.println("大家说拜拜,参与人数："+registeredParties);
                System.out.println();
                // 返回false 接着执行 返回true结束
                return false;
            case 3:
                System.out.println("过小日子,参与人数："+registeredParties);
                // 返回false 接着执行 返回true结束
                return true;
            default:
                return true;
        }
    }
}

class Person implements Runnable{
    public Person(){}
    public Person(String name, Phaser phaser) {
        this.name = name;
        this.phaser = phaser;
    }
    // 名称
    private String name;
    private Phaser phaser;


    @Override
    public void run() {
        // 到达
        arrived();
        // 准备吃饭
        eat();
        // 准备走了
        go();
        // 生活
        life();
    }

    public void arrived(){
        sleepRandom();
        System.out.println(name+"到了~");
        // 到达 类似于CyclicBarrier 的await()
        phaser.arriveAndAwaitAdvance();
    }
    public void eat(){
        sleepRandom();
        System.out.println(name+"准备吃饭~");
        phaser.arriveAndAwaitAdvance();
    }
    public void go(){
        sleepRandom();
        System.out.println(name+"准备走了~");
        phaser.arriveAndAwaitAdvance();
    }
    public void life(){
        sleepRandom();
        if (name.contains("新人")) {
            System.out.println(name+"过日子~");
            phaser.arriveAndAwaitAdvance();
        } else {
            // 取消注册 phaser.bulkRegister(5); 本来是5个人 这里取消一次就少一个人
            phaser.arriveAndDeregister();
        }
    }


    // 随机sleep几秒 来模拟人的不确定性
    public void sleepRandom(){
        try{
            TimeUnit.SECONDS.sleep((long) (Math.random()*10+1));
        } catch (Exception e) {
            System.out.println("异常了~~");
        }
    }
}
