package config;

import org.apache.ibatis.mapping.DatabaseIdProvider;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程
 * 一个生活在互联网底层，做着增删改查的码农,不谙世事的造作
 * @create 2021-09-02 21:26
 */
public class MyDatabaseIdProvider implements DatabaseIdProvider {

    @Override
    public void setProperties(Properties p) {

    }

    @Override
    public String getDatabaseId(DataSource dataSource) throws SQLException {
        return null;
    }
}
