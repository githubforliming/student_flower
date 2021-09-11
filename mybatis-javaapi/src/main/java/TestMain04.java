import com.sun.corba.se.impl.ior.TaggedProfileTemplateFactoryFinderImpl;
import dao.PersonMapper;
import entity.Person;
import org.apache.ibatis.session.SqlSession;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程
 * 一个生活在互联网底层，做着增删改查的码农,不谙世事的造作
 * @create 2021-09-04 10:26
 */
public class TestMain04 {

    public static void main(String[] args){
        try (SqlSession sqlSession = SqlSessionUtil.getSession()) {
            PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
            // 3. 新增值
            List<Person> list = mapper.select01();
            System.out.println(list);

        }
    }
}
