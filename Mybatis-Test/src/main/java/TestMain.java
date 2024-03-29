import dao.TestMapper;
import entity.TestEntity;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.List;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程
 * 一个生活在互联网底层，做着增删改查的码农,不谙世事的造作
 * @create 2021-08-25
 */
public class TestMain {
    public static void main(String[] args) throws Exception {
        // 1. mybatis 配置文件
        String resource = "mybatis-config.xml";
        // 2. 获取输入流
        InputStream inputStream = Resources.getResourceAsStream(resource);
        // 3. 创建SqlSessionFactory工厂 这一步会进行Mapper的动态代理操作
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        // 4. 创建SqlSession
        try (SqlSession session = sqlSessionFactory.openSession()) {
            // 5. 通过sesson获取Mapper 这个Mapper会编程Mybatis的代理Mapper
            TestMapper mapper = session.getMapper(TestMapper.class);
            // 6. 调用方法
            mapper.delete(1L);
            // 7. 手动提交
            session.commit();
        }
    }
}
