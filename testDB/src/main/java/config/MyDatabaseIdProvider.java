package config;

import org.apache.ibatis.mapping.DatabaseIdProvider;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程
 * 一个生活在互联网底层，做着增删改查的码农,不谙世事的造作
 * @create 2021-09-03 21:35
 */
public class MyDatabaseIdProvider implements DatabaseIdProvider {
    Properties prop ;

    // 这个properties就是 mybatis-config.xml中 databaseIdProvider的所有property
    @Override
    public void setProperties(Properties p) {
        this.prop = p;
    }

    @Override
    public String getDatabaseId(DataSource dataSource) throws SQLException {
        // 这里自己判断类型返回
        // 获取连接
        Connection con = null;
        try {
            con = dataSource.getConnection();
            // 获取元信息
            DatabaseMetaData metaData = con.getMetaData();
            // 获取数据库产品名称
            String productName =  metaData.getDatabaseProductName();
            System.out.println("产品名称："+productName);
            if (this.prop != null) {
                for (Map.Entry<Object, Object> property : prop.entrySet()) {
                    String key = (String) property.getKey();
                    String value = (String) property.getValue();
                    System.out.println("key="+key+",value="+value);
                    if (productName.contains(key)) {
                        return value;
                    }
                }
                // 默认返回空
                return null;
            }
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    // ignored
                }
            }
        }
        // 默认返回空
        return null;
    }
}
