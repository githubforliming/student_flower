package com.example.wxtest.hello.entity;

/**
 * @author 木子的昼夜编程
 */
public class Demo {
  String name;
  int age;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  @Override
  public String toString() {
    return "Demo{" +
            "name='" + name + '\'' +
            ", age=" + age +
            '}';
  }
}
