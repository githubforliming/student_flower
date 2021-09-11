package com.example.student_flower.dao;

import com.example.student_flower.entity.TStudentFlower;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程
 * 一个生活在互联网底层，做着增删改查的码农,不谙世事的造作
 * @create 2021-09-11 10:14
 */
@Mapper
public interface TStudentFlowerMapper  {
    // 插入
    void insert(TStudentFlower tStudentFlower);
    // 更新
    void update(TStudentFlower tStudentFlower);

    // 查询
    TStudentFlower selectByClassroomIdAndStudentId(@Param("classroomId") Long classroomId,
                                                          @Param("studentId") Long studentId);
}
