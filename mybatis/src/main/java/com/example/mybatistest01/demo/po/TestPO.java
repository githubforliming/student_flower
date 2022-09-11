package com.example.mybatistest01.demo.po;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author 木子的昼夜编程
 */
public class TestPO {
    private Long id;
    private String name;
    private BigDecimal money;
    private LocalDateTime time;

    @Override
    public String toString() {
        return "TestPO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money=" + money +
                ", time=" + time +
                '}';
    }
}
