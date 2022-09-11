import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import dao.TestMapper;
import entity.TestEntity;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程
 * 一个生活在互联网底层，做着增删改查的码农,不谙世事的造作
 */
public class TestMain08 {
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
            List<TestEntity> all = mapper.select();
            System.out.println("总条数："+all.size());
            // 1. 参数方式
            // https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/en/HowToUse.md
            List<TestEntity> res = mapper.select01(pageNum, pageSize);
            System.out.println("分页条数01："+res.size());
            // 2. 支持  ServletRequest,Map,POJO 对象，
            // request难获取，这里举一个Map 的例子吧
            Map<String, Integer> params = new HashMap<>();
            params.put("myPageNum", pageNum);
            params.put("myPageSize", pageSize);
            List<TestEntity> res02 = mapper.select02(params);
            System.out.println("分页条数02："+res02.size());
            // 可以看到这里返回类型用List接收的，但是此list非彼list
            // 其实他返回的是一个com.github.pagehelper.Page 他继承了ArrayList
            // 这个page除了我们看到的数据以为 还有很多高级的东西
            // 我们可以直接转换成他提供的PageInfo
            PageInfo pageInfo = new PageInfo(res02);
            System.out.println("当前页数pageNum:"+pageInfo.getPageNum());
            System.out.println("分页大小pageSize:"+pageInfo.getPageSize());
            System.out.println("上一页页数prePage:"+pageInfo.getPrePage());
            System.out.println("一共多少页:"+pageInfo.getPages());
            System.out.println("当前页条数:"+pageInfo.getSize());
            System.out.println("总条数:"+pageInfo.getTotal());
            System.out.println("记录开始行数:"+pageInfo.getStartRow());
            System.out.println("记录结束行数:"+pageInfo.getEndRow());

            System.out.println("------------华丽丽的分割线-----------------");
            // 或者强转成Page使用也是ok的
            Page page = (Page)res02;
            System.out.println("当前页数pageNum:"+page.getPageNum());
            System.out.println("分页大小pageSize:"+page.getPageSize());
            System.out.println("一共多少页:"+page.getPages());
            System.out.println("当前页条数:"+page.size());
            System.out.println("总条数:"+page.getTotal());
            System.out.println("记录开始行数:"+page.getStartRow());
            System.out.println("记录结束行数:"+page.getEndRow());


        }
    }
}
