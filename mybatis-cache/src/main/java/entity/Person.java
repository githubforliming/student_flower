package entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程
 * 一个生活在互联网底层，做着增删改查的码农,不谙世事的造作
 * @create 2021-08-30 21:45
 */
public class Person implements Serializable {
    private Long id;
    private String name;
    private String jobName;
    private BigDecimal salary;
    private Integer age;
    private String gender;
    private String address;
    private String hobby;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", jobName='" + jobName + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}
