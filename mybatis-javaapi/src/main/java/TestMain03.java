
import java.sql.SQLException;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程
 * 一个生活在互联网底层，做着增删改查的码农,不谙世事的造作
 * @create 2021-09-04 10:26
 */
public class TestMain03 {

    public static void main(String[] args) throws SQLException {

     /*   try (SqlSession sqlSession = SqlSessionUtil.getSession()) {
            PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
            // 1. 查询
            System.out.println("初始值：");
            List<Person> list = mapper.select();
            System.out.println(list);
            // 2. 删除
            mapper.delete(1L);
            System.out.println("删除后的值：");
            List<Person> list02 = mapper.select();
            System.out.println(list02);
            // 3. 新增值
            Person insert =  new Person();
            insert.setName("小明");
            insert.setAge(18);
            insert.setJobName("开发工程师（初级）");
            insert.setSalary(BigDecimal.valueOf(60000));
            mapper.insert(insert);
            System.out.println("新增后的值：");
            List<Person> list03 = mapper.select();
            System.out.println(list03);
            // 4. 更新值 给小月月涨工资
            mapper.update(2L, BigDecimal.valueOf(888888));
            System.out.println("更新后的值：");
            List<Person> list04 = mapper.select();
            System.out.println(list04);
            // 5. 查询工资是5万到10万的员工
            List<Person> lists = mapper.listBySalary(BigDecimal.valueOf(50000), BigDecimal.valueOf(100000));
            System.out.println("根据薪资范围查询的值：");
            System.out.println(lists);*/
    }
}
