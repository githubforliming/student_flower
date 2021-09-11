package dao;


import entity.Person;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程  分享一个生活在互联网底层做着增删改查的码农的感悟与学习
 * @create 2021-08-30 21:54
 */
public interface PersonMapper000 {

    // 列表
    @Select({"select id , `name`,age,salary,job_name jobName from person"})
    List<Person> select();

    // 增加
    @Insert("insert into person (`name`,job_name,salary,age) value (#{name},#{jobName},#{salary},#{age})")
    void insert(Person p);

    // 修改工资
    @Update("update person set salary = #{salary} where id = #{id}")
    void update(@Param("id") Long id, @Param("salary") BigDecimal salary);

    // 删除记录
    @Delete("delete from person where id = #{id}")
    void delete(Long id);

    // 根据薪资范围查询person
    @Select("select  id ,  name,age,salary,job_name jobName from person where salary between #{start} and #{end}")
    List<Person> listBySalary(@Param("start") BigDecimal start, @Param("end") BigDecimal end);


}
