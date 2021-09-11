import java.math.BigDecimal;
import java.sql.*;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程
 * 一个生活在互联网底层，做着增删改查的码农,不谙世事的造作
 */
public class TestMain02 {
    public static void main(String[] args) throws Exception {
        Connection conn = null;
        Statement stmt = null;
        ResultSet result = null;
        try {
            // 1. 注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 2. 打开连接 url 、 用户名、 密码
            conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/test?useSSL=false",
                    "root",
                    "123456");
            // 3. 创建Statenebt
            stmt = conn.createStatement();
            // 4. 执行查询
            result = stmt.executeQuery("select * from test where name = '小强'");
            // 5. 输出数据库获取的结果
            while (result.next()){
                // 根据列名称获取值
                String name = result.getString("name");
                BigDecimal salary = result.getBigDecimal("salary");
                System.out.println(name+" 的工资是："+salary);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            // 关闭资源
            try{
                if(result!=null) {
                    result.close();
                }
            }catch(SQLException se2){
                System.err.println("关闭result异常");
            }
            try{
                if(stmt!=null) {
                    stmt.close();
                }
            }catch(SQLException se2){
                System.err.println("关闭stmt异常");
            }
            try{
                if(conn!=null) {
                    conn.close();
                }
            }catch(SQLException se){
                System.err.println("关闭conn异常");
            }
        }

    }
}
