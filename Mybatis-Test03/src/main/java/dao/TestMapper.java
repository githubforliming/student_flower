package dao;

import entity.TestEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程  分享一个生活在互联网底层做着增删改查的码农的感悟与学习
 * @create 2021-08-25 22:07
 */
public interface TestMapper {
    // 新增
    void save(TestEntity testEntity);

    // 修改
    void update(TestEntity testEntity);

    // 删除 这里就一个参数 所以不用@Param 也不用Map 自定义实体类等
    void delete(Long id);

    // 根据主键查询
    TestEntity get(Long id);

    // 查询所有数据
    List<TestEntity> list();

    // 根据名称模糊查询
    List<TestEntity> listByNameLike(String name);
    // 根据名称模糊查询
    List<TestEntity> listByNameAndAge(@Param("name") String name,@Param("age") Integer age);

    // 多参数Map 方式传递
    List<TestEntity> listByNameAndAgeMap(Map<String, Object> param);

    // 什么都不用
    List<TestEntity> listByNameAndAgeNone(String name, int age);

    // 根据年龄集合查询
    List<TestEntity> listByAges(int[] ages);

    // 根据多组参数查询
    List<TestEntity> listByNameAndAges(TestEntity[] params);

    List<TestEntity> listByNameAndAgesList(List<TestEntity> params);

    // #{}  根据名称查询数据
    List<TestEntity> listByName01(@Param("name") String name);

    // ${}  根据名称查询数据
    List<TestEntity> listByName02(@Param("name") String name);
}
