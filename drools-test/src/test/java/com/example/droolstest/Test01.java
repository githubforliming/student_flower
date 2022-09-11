package com.example.droolstest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 木子的昼夜编程
 */
public class Test01 {
    public static void main(String[] args) {
        // 充值金额
        int amont = 340;
        // 获取原积分
        int score = 0;
        Strategy strategy = WhichStrategy.getStrategy(amont);
        Context context = new Context(strategy);
        System.out.println(context.addScore(score));
    }


}
class Context{
    Strategy strategy;
    public Context(Strategy strategy){
        this.strategy = strategy;
    }
    int addScore(int score){
        return strategy.addScore(score);
    }
}
class WhichStrategy{
    int start;
    int end;
    Strategy strategy;
    public WhichStrategy(int start, int end, Strategy strategy) {
        this.start = start;
        this.end = end;
        this.strategy = strategy;
    }
    static List<WhichStrategy> allStrategy = new ArrayList<>();
    static {
        allStrategy.add(new WhichStrategy(0,100, new Strategy01()));
        allStrategy.add(new WhichStrategy(100,500, new Strategy02()));
        allStrategy.add(new WhichStrategy(500,1000, new Strategy03()));
        allStrategy.add(new WhichStrategy(1000,Integer.MAX_VALUE, new Strategy04()));
    }

    public static Strategy getStrategy(int amont){
        if (amont <0 ) {
            throw new RuntimeException("闹着玩呢？充值负数？");
        }
        for (int i = 0; i < allStrategy.size(); i++) {
            WhichStrategy whichStrategy= allStrategy.get(i);
            if (whichStrategy.start < amont && whichStrategy.end >= amont) {
                return whichStrategy.strategy;
            }
        }
        throw new RuntimeException("没有匹配到策略！");
    }
}
interface Strategy{
    int addScore(int score);
}
class Strategy01 implements Strategy{
    @Override
    public int addScore(int score) {
        return score + 0;
    }
}
class Strategy02 implements Strategy{
    @Override
    public int addScore(int score) {
        return score + 100;
    }
}
class Strategy03 implements Strategy{
    @Override
    public int addScore(int score) {
        return score + 500;
    }
}
class Strategy04 implements Strategy{
    @Override
    public int addScore(int score) {
        return score + 100;
    }
}