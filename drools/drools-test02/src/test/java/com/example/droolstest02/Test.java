package com.example.droolstest02;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author 木子的昼夜编程
 */
public class Test {
    public static void main(String[] args) {
        String str = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));
        System.out.println("日期："+str);
    }
}
