package sql;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程
 * 一个生活在互联网底层，做着增删改查的码农,不谙世事的造作
 * @create 2021-09-04 19:06
 */
public class MySqlBuilder {

    public static String listByNameAndAge(@Param("name") final String name, @Param("age") final Integer age){
        System.out.println("自定义SQL");
        return new SQL(){{
            SELECT("*");
            FROM("person  ");
            WHERE(" 1=1 ");
            if (name != null) {
                AND().WHERE("name like concat('%',#{name},'%')");
            }
            if (age != null) {
                AND().WHERE("age = #{age}");
            }
            // 排序
            ORDER_BY("id");
        }}.toString();
    }
}
