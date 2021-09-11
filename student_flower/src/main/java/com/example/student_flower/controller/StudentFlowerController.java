package com.example.student_flower.controller;

import com.example.student_flower.common.anotation.MyAnotation;
import com.example.student_flower.service.StudentFlowerService;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程
 * 一个生活在互联网底层，做着增删改查的码农,不谙世事的造作
 * @create 2021-09-11 10:35
 */
@RestController
public class StudentFlowerController {

    @Autowired
    StudentFlowerService studentFlowerService;

    /**
     *
     * @param classroomId 教师ID
     * @param studentId 学生ID
     */
    @MyAnotation(redisKey = "/test/sendflower", params = {"classroomId", "studentId"})
    @GetMapping(value = "/test/sendflower/{classroomId}/{studentId}")
    public void sendFlower(@NotNull  @PathVariable("classroomId") Long classroomId , @NotNull @PathVariable("studentId") Long studentId){
        studentFlowerService.SendFlower(classroomId,studentId);
    }
}
