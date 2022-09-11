package com.example.droolstest.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 木子的昼夜编程
 */
@Data
@Accessors(chain = true)
public class User {
    /**
     * 姓名
     */
    private String name;
    /**
     * 用户级别
     */
    private int level;
}
