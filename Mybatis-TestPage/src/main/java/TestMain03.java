import dao.TestMapper;
import entity.TestEntity;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.InputStream;
import java.util.*;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程
 * 一个生活在互联网底层，做着增删改查的码农,不谙世事的造作
 */
public class TestMain03 {
    public static void main(String[] args) throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            // 通过sesson获取Mapper 这个Mapper会编程Mybatis的代理Mapper
            TestMapper mapper = session.getMapper(TestMapper.class);
            // 业务传递进来的分页参数
            Integer pageNum = 1; // 当前页(一般与前端交互 就迁就一下他们 页数从1开始)
            Integer pageSize = 10; // 每页大小
            // 查询全部数据  有时候可能调用第三方或者其他接口 只有全部数据
            // 又想分页展示 但是人家不给你提供分页接口 这时候可能会用到这种
            List<TestEntity> list = mapper.select();
            List<TestEntity> res = new ArrayList<>();
            if (list != null) {
                // Params:
                //fromIndex – low endpoint (包含) of the subList
                //toIndex – high endpoint (不包含) of the subList
                // 如果符合： (fromIndex < 0 || toIndex > size || fromIndex > toIndex) 抛异常 IndexOutOfBoundsException –
                int size = list.size();
                // 防止 fromIndex < 0
                if (pageNum <= 0) {
                    throw  new Exception("pageNum必须是大于等于1的整数");
                }
                if (pageSize <= 0) {
                    throw  new Exception("pageSize必须是大于等于1的整数");
                }
                int totalPage = (int) Math.ceil(Double.valueOf(size)/Double.valueOf(pageSize));
                // 页数超了最大页数 （前端不可信）
                if (pageNum > pageSize) {
                    res = new ArrayList<>();
                } else {
                    Integer fromIndex = (pageNum -1 ) * pageSize;
                    Integer toIndex =pageNum * pageSize;
                    // 防止 toIndex > size
                    toIndex = toIndex > list.size() ? list.size() : toIndex;
                    res = list.subList(fromIndex, toIndex);
                }
            }
            for (int i = 0; i < res.size(); i++) {
                System.out.println(res.get(i));
            }
        }
    }
}
