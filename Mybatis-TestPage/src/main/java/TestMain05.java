import dao.TestMapper;
import entity.TestEntity;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程
 * 一个生活在互联网底层，做着增删改查的码农,不谙世事的造作
 */
public class TestMain05 {
    public static void main(String[] args) throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            // 通过sesson获取Mapper 这个Mapper会编程Mybatis的代理Mapper
            TestMapper mapper = session.getMapper(TestMapper.class);
            // offset偏移量从0开始  limit一共查几条
            Integer pageNum =1; // 当前页(一般与前端交互 就迁就一下他们 页数从1开始)
            Integer pageSize = 10; // 每页大小
            // 分页参数错误判断就先不判断了 根据业务自己判断
            List<TestEntity> res = mapper.selectLimit((pageNum-1)*pageSize, pageSize);
            for (int i = 0; i < res.size(); i++) {
                System.out.println(res.get(i));
            }
        }
    }
}
