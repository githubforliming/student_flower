package com.example.student_flower.common.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程  分享一个生活在互联网底层做着增删改查的码农的感悟与学习
 *
 * 关于自定义注解 后边有机会专门写一写 先会用
 * @create 2021-09-11 15:26
 */
@Target({ElementType.METHOD}) // 方法上使用的注解
@Retention(RetentionPolicy.RUNTIME) // 运行时通过反射访问
public @interface MyAnotation {

    /**
     * 锁过期时间
     */
    int expireTime() default 20;

    /**
     * 锁key值
     */
    String redisKey() default "";

    /**
     * 锁key后拼接的动态参数的值
     */
    String[] params() default {};
}
