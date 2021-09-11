package com.example.student_flower.service;

import com.example.student_flower.dao.TStudentFlowerMapper;
import com.example.student_flower.entity.TStudentFlower;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程
 * 一个生活在互联网底层，做着增删改查的码农,不谙世事的造作
 * @create 2021-09-11 10:38
 */
@Service
public class StudentFlowerService {
    @Autowired
    TStudentFlowerMapper mapper;

    public void SendFlower(Long classroomId, Long studentId){
        TStudentFlower tStudentFlower = mapper.selectByClassroomIdAndStudentId(classroomId, studentId);
        // 第一次送花 没有记录 新增
        if (tStudentFlower == null) {
            TStudentFlower tsf = new TStudentFlower();
            tsf.setClassroomId(classroomId);
            tsf.setStudentId(studentId);
            tsf.setFlowerNum(1);
            mapper.insert(tsf);
        } else {
            // 已经送过花了 原来数量上+1
            tStudentFlower.setFlowerNum(tStudentFlower.getFlowerNum() + 1);
            mapper.update(tStudentFlower);
        }
    }
}
