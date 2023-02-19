package com.freeedu.test;

import java.util.Arrays;

/**
 * @author 木子的昼夜编程
 */
public class BubbTest {
    public static void main(String[] args) {
        // int[] arr = {1,2,3,4,2,2,34,45,3,2,2,33,33,445566,777,7,54,33,344,4,33};
        // int[] arr = {1};
        // int[] arr = null;
        int[] arr = {1,2,-3,4,2,2,-34,45,3,2,2,33,-33,-445566,777,7,54,33,344,4,33};
        doBubb(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void doBubb(int[] arr){
        // 判空
        if (arr == null || arr.length <2 ) {
            return;
        }
        // 假设是4
        int N = arr.length;
        // 3 2 1
        for (int i = N-1; i > 0; i--) {
            // 3: 0 1 2
            // 2: 0 1
            // 1: 0
            for (int j = 0; j < i ; j++) {
                // 如果前一个 > 后一个  交换  冒泡，就是大的往后靠
                if (arr[j] > arr[j+1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = tmp;
                }
            }       
        }
    }
}
