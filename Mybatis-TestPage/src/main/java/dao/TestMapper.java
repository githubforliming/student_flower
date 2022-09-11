package dao;

import entity.PageQo;
import entity.TestEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程  分享一个生活在互联网底层做着增删改查的码农的感悟与学习
 * @create 2021-08-25 22:07
 */
public interface TestMapper {
   @Select("select * from test")
   List<TestEntity> select();

   @Select("select * from test")
   List<TestEntity> selectRowBounds(RowBounds rd);

   @Select("select * from test limit #{offset} , #{pageSize}")
   List<TestEntity> selectLimit(@Param("offset") int offset, @Param("pageSize") Integer pageSize);

   @Select("select * from test")
   List<TestEntity> selectPage(PageQo pageQo);

    @Select("select * from test")
    List<TestEntity> select01(@Param("myPageNum") Integer pageNum, @Param("myPageSize") Integer pageSize);

    @Select("select * from test")
    List<TestEntity> select02(Map<String, Integer> params);
}
