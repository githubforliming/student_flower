/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程
 * 一个生活在互联网底层，做着增删改查的码农,不谙世事的造作
 */
public class TestMain06 {
    public static void main(String[] args)   {
        String sql = "select * from test where name = ?";
        String name = "'小强'";
        sql = sql.replace("?", name);
        System.out.println(sql);
    }
}
