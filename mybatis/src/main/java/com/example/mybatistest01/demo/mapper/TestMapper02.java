package com.example.mybatistest01.demo.mapper;

import com.example.mybatistest01.demo.po.TestPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 木子的昼夜编程
 */
@Mapper
public interface TestMapper02 {
     int count();

     TestPO getById(Long id);
}
