import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import dao.PersonMapper;
import entity.Person;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程
 * 一个生活在互联网底层，做着增删改查的码农,不谙世事的造作
 * @create 2021-09-04 17:15
 */
public class SqlSessionUtil {
    // 数据库配置
    private static String user = "root";
    private static String password = "123456";
    private static Integer port = 3306;
    private static String url = "jdbc:mysql://127.0.0.1:3306/test";
    public static SqlSession getSession(){
        // 1. 创建 DataSources
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser(user);
        dataSource.setPassword(password);
        dataSource.setPort(port);
        dataSource.setUrl(url);
        dataSource.setUseSSL(true);
        // 2. 创建 TransactionFactory
        TransactionFactory tsf = new JdbcTransactionFactory();
        // 3. 创建 Environment
        Environment ev = new Environment("development", tsf, dataSource);
        // 4. 创建Configuration
        Configuration cfg = new Configuration(ev);
        // 4.1 注册别名
        cfg.getTypeAliasRegistry().registerAlias("person", Person.class);
        // 也可以直接指定包路径
        // cfg.getTypeAliasRegistry().registerAliases("entity");
        // 4.2 添加Mapper映射
        cfg.addMapper(PersonMapper.class);
        // 也可以直接指定包名
        //cfg.addMappers("dao");
        // 5. 创建SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
        // 6. 创建SqlSessionFactory
        SqlSessionFactory factory  = ssfb.build(cfg);
        return factory.openSession();
    }
}
