package dao;


import entity.Person;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import sql.MySqlBuilder;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程  分享一个生活在互联网底层做着增删改查的码农的感悟与学习
 * @create 2021-08-30 21:54
 */
public interface PersonMapper {

    @Insert("insert into person (`name`,job_name,salary,age) value (#{name},#{jobName},#{salary},#{age})")
    @SelectKey(resultType = Long.class, keyProperty = "id",
            statement = {"SELECT LAST_INSERT_ID()"}, before = false)
    Long insert(Person p);

    @Select({
            "<script>",
            "select * from person ",
            "<where>",
            "<if test='name != null and name !=\"\" '>name like concat('%',#{name},'%')</if>",
            "</where>",
            "</script>"
    })
    List<Person> select(@Param("name") String name);

    @SelectProvider(type = MySqlBuilder.class , method = "listByNameAndAge")
    List<Person> selectByAgeAndName(@Param("name") String name, @Param("age") Integer age);


    @Results(id="personResult", value = {
            @Result(property = "name",column = "name" , javaType = String.class ,jdbcType = JdbcType.VARBINARY),
            @Result(property = "jobName",column = "job_name" , javaType = String.class ,jdbcType = JdbcType.VARBINARY),
            @Result(property = "age",column = "age" , javaType = Integer.class ,jdbcType = JdbcType.INTEGER),
            @Result(property = "salary",column = "salary" , javaType = BigDecimal.class ,jdbcType = JdbcType.DECIMAL)
    })
    @Select("select * from person")
    List<Person> select02();


    @ResultType(value = Person.class)
    @Select("select * from person")
    List<Person> select01();

    @ResultMap(value = ")
    @Select("select * from person")
    List<Person> select03();
}
