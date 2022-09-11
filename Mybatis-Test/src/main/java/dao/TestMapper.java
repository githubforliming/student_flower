package dao;

import entity.TestEntity;

import java.util.List;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程  分享一个生活在互联网底层做着增删改查的码农的感悟与学习
 * @create 2021-08-25 22:07
 */// 新增
    void save(TestEntity testEntity);

            // 修改
            void update(TestEntity testEntity);

            // 删除 这里就一个参数 所以不用@Param 也不用Map 自定义实体类等
            void delete(Long id);

            // 根据主键查询
            TestEntity get(Long id);

// 查询所有数据
public interface TestMapper {

    List<TestEntity> list();

    // 根据名称模糊查询
    List<TestEntity> listByNameLike(String name);
}
