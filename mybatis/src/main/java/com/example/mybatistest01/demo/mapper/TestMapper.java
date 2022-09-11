package com.example.mybatistest01.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author 木子的昼夜编程
 */
@Mapper
public interface TestMapper {
    // 直接用注解的方式 不用写xml （我个人不推荐这样写  虽然感觉快 但是sql一旦多了就很乱 还是各司其职吧）
    // 查询表aaaa的总记录数
    @Select("select count(1) from aaaa")
    public int count();
}
