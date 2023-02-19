package com.freeedu.test;

/**
 * @author 木子的昼夜编程
 */
public class Test01 {
    public static void main(String[] args) {
        int[] arr = {3,4,8,7,6,9,2};
        // 原数组
        print(arr);
        selectSort(arr);
        System.out.println();
        // 排序后数组
        print(arr);
    }

    // 打印数组
    private static void print(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("空的");
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    // 选择排序  传递数组
    public static void selectSort(int[] arr) {
        // 遍历n次
        for (int i = 0; i < arr.length; i++) {
            // 记录这次最小位置下标 等着最后与i下标交换
            int index = i;
            // 遍历剩余数 找最小数下表
            for (int j = i; j < arr.length; j++) {
                // 如果找到一个比当前index小的数 替换index
                index = arr[j] < arr[index] ? j : index;
            }
            // 交换 如果最小下标是自己，就不交换了。
            if (index != i) {
                // 先记录i位置的值
                int tmp = arr[i];
                // 设置i位置的值为index位置的值
                arr[i] = arr[index];
                // 设置index位置的值为开始记录的i位置的值 tmp
                arr[index] = tmp;
            }
        }
    }
}
