package com.freeedu.test;

/**
 * @author 木子的昼夜编程
 */
public class Test {
    public static void main(String[] args) {
        // 测试第一个
        System.out.println("第一个函数测试===");
        printBinary(17);
        // 测试第二个
        System.out.println("\n第二个函数测试===");
        printBinary02(17);
    }
    
    public static void printBinary(int num) {
        //
        for (int i = 31; i >=0 ; i--) {

            // 8个数输出一个下划线
            if (i != 31 && (i+1) % 8 == 0) {
                System.out.print("_");
            }
            // 这就是视频中讲的 判断结果是否等于0
            int zo = num & (1 << i) ;
            if (zo == 0) {
                // 如果等于0 表示对应位上是0  输出0
                System.out.print(0);
            } else {
                // 如果不是0 就输出1
                System.out.print(1);
            }
        }
    }

    public static void printBinary02(int num) {
        for (int i = 31; i >=0 ; i--) {
            // 8个数输出一个下划线
            if (i != 31 && (i+1) % 8 == 0) {
                System.out.print("_");
            }
            // 三元运算。
            int zo = (num & (1 << i)) == 0 ? 0 : 1 ;
            System.out.print(zo);
        }
    }

}
