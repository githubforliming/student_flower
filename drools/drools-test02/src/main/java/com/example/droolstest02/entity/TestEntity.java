package com.example.droolstest02.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author 木子的昼夜编程
 */
@Data
@Builder
@ToString
public class TestEntity {
    List<String> list;
    String[] arr;
    String names;
}
