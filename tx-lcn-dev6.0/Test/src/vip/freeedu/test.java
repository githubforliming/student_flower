package vip.freeedu;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 木子的昼夜编程
 */
public class test {
    // 初始化map1
    static Map<String, String> map1 = new HashMap<>();
    static {
        map1.put("01","大帅哥");
        map1.put("02","小奶狗");
        map1.put("03","身体好");
        map1.put("04","大叔");
    }

    // 初始化map2
    static Map<String, String> map2 = new HashMap<>(){{
        put("01","大帅哥");
        put("02","小奶狗");
        put("03","身体好");
        put("04","大叔");
    }};

    Map.of
    public static void main(String[] args) {
        System.out.println(map1.get("01"));
        System.out.println(map1.get("02"));
    }
}
