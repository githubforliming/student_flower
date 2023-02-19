package com.example.droolstest02.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author 木子的昼夜编程
 */
@Data
@Builder
public class TestQueryEntity {
    String name;
    Integer age;
}
