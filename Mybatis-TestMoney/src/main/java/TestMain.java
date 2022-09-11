import dao.ProductMapper;
import entity.Product;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

import java.io.InputStream;
import java.util.List;


public class TestMain {
    public static void main(String[] args) throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        try (SqlSession session = sqlSessionFactory.openSession()) {
            // 通过sesson获取Mapper 这个Mapper会编程Mybatis的代理Mapper
            ProductMapper mapper = session.getMapper(ProductMapper.class);
            Product p = new Product();
            p.setName("小爱吹风机");
            // 价格是100块5毛人民币 这里需要注意人民币最小单位是分 所以这里最多只能使用两位小数的数据
            // 如果是类似：100.093 就会报错 （因为他不知道是给你进位还是怎么处理）
            Money money = Money.of(CurrencyUnit.of("CNY"), 100.5);
            p.setPrice(money);
            mapper.insert(p);
            session.commit();

            List<Product> list = mapper.list();
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }

        }
    }
}
