package dao;

import entity.Person;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程  分享一个生活在互联网底层做着增删改查的码农的感悟与学习
 * @create 2021-08-30 21:54
 */
public interface PersonMapper {
    List<Person> list();

    List<Person> testMap(@Param("pmap") Map<String, String> map);

    List<Person> testChoose(@Param("name") String name);

    // 测试bind
    List<Person> testBind(@Param("name") String name);

    // 测试返回当前时间
    String testDb();
}
