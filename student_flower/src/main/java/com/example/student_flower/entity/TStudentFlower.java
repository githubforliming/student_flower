package com.example.student_flower.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程
 * 一个生活在互联网底层，做着增删改查的码农,不谙世事的造作
 * @create 2021-09-11 10:09
 *
 * 这里先手动编写 后期有时间写一下 怎么用辅助工具直接生成包括但不限于Controller Service Dao Entity
 */
@Data
public class TStudentFlower implements Serializable {
    private Long id;
    private Long studentId;
    private Long classroomId;
    private Integer flowerNum;
}
