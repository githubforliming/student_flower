package com.example.student_flower.common.aspect;

import com.example.student_flower.common.anotation.MyAnotation;
import com.example.student_flower.util.HttpContextUtils;
import com.example.student_flower.util.SpelUtil;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author 发现更多精彩  关注公众号：木子的昼夜编程
 * 一个生活在互联网底层，做着增删改查的码农,不谙世事的造作
 *
 * 关于spring面向切面的知识 等以后文章有机会我写一写（自己也不太熟 暂时会用）
 *
 * @create 2021-09-11 15:29
 */
@Slf4j
@Aspect
@Component
public class MyAspect {

    @Autowired
    RedissonClient redissonClient;

    // 这个是那些方法需要被切 -- 被标记注解MyAnotation的方法要被切
    @Pointcut("@annotation(com.example.student_flower.common.anotation.MyAnotation)")
    public void whichMethodAspect() {
    }

    /**
     * 切面 执行业务逻辑 在实际业务方法执行前 后 都可以进行一些额外的操作
     * 切面的好处就是对你不知不觉
     */
    @Around("whichMethodAspect()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 1. 获取注解
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        MyAnotation myAnotation = method.getAnnotation(MyAnotation.class);

        // 2. 锁等待时间
        int waitTime = myAnotation.waitTime();
        // 2. 锁超时时间 怕万一finally没有被执行到的时候 多长时间自动释放锁（基本不会不执行finnaly 除非那个点机器down了）
        final int lockSeconds = myAnotation.expireTime();
        // 3. 特殊业务自定义key
        String key = myAnotation.redisKey();
        // 自定义redisKey是否使用参数
        String[] params = myAnotation.params();
        // 4.获取HttpServletRequest
        HttpServletRequest request = HttpContextUtils.getRequest();
        if (request == null) {
            throw new Exception("错误的请求 request为null");
        }
        assert request != null;

        // 5. 组合redis锁key
        // 5.1 如果没有自定义 用默认的 url+token
        if (StringUtils.isBlank(key) && (params == null || params.length == 0)) {
            // 这里怎么获取token 主要看自己项目用的什么框架 token在哪个位置存储着
            String token = request.getHeader("Authorization");
            String requestURI = request.getRequestURI();
            key = requestURI+token;
        } else {
            // 5.2 自定义key
            key = SpelUtil.generateKeyBySpEL(key, params, joinPoint);
        }
        // 6. 获取key
        // 获取锁 获取不到最多等waitTime秒 lockSeconds秒后自动释放锁
        // 每个项目组应该会有自己的redisUtil的封装 我这里就用最简单的方式
        // 怎么使用锁不是重点 重点是这个思想
        RLock lock = redissonClient.getLock(key);
        log.info("tryLock key = {}", key);
        boolean b = lock.tryLock(waitTime, lockSeconds, TimeUnit.SECONDS);
        // 获取锁成功
        if (b) {
            try {
                log.info("tryLock success, key = {}", key);
                // 7. 执行业务代码 返回结果
                return joinPoint.proceed();
            } finally {
                lock.unlock();
            }
        } else {
            // 获取锁失败
            log.info("tryLock fail, key = {}", key);
            throw new Exception("请求频繁，请稍后重试");
        }
    }

}