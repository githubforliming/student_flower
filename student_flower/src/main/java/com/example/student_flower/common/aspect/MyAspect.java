package com.example.student_flower.common.aspect;

import com.example.student_flower.common.anotation.MyAnotation;
import com.example.student_flower.util.SpelUtil;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

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
        MyAnotation ide = method.getAnnotation(MyAnotation.class);

        // 2. 锁超时时间 怕万一finally没有被执行到的时候 多长时间自动释放锁（基本不会不执行finnaly 除非那个点机器down了）
        final int lockSeconds = ide.expireTime();
        // 3. 特殊业务自定义key
        String key = ide.redisKey();
        // 自定义redisKey是否使用参数
        String[] params = ide.params();
        // 4.获取HttpServletRequest
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        Asserts.checkNotNull(request, "request can not null");
        assert request != null;
        // 如果没有自定义key 需要按照规则生成key
        if (StringUtils.isBlank(key) && (params == null || params.length == 0)) {
            String token = request.getHeader("Authorization");
            String requestURI = request.getRequestURI();
            log.info("tryLock success, token = [{}], requestURI = [{}]", token, requestURI);
            key = getIdeKey(token, requestURI);
        } else {
            // 解析表达式
            key = SpelUtil.generateKeyBySpEL(key, params, joinPoint);
        }
        // 3 秒内默认请求
        int tryLock = 3;
        // 获取锁 获取不到最多等3秒 lockSeconds秒后自动释放锁
        boolean b = RedissonLockUtil.tryLock(key, tryLock, lockSeconds);
        log.info("tryLock key = {}", key);
        if (b) {
            log.info("tryLock success, key = {}", key);
            // 获取锁成功
            Object result;
            try {
                // 执行进程
                result = joinPoint.proceed();
            } finally {
                // 解锁q
                RedissonLockUtil.unlock(key);
                log.info("releaseLock success, key = {}", key);
            }
            return result;
        } else {
            // 获取锁失败，认为是重复提交的请求
            log.info("tryLock fail, key = {}", key);
            log.info("tryLock fail, key = {}", "重复请求，请稍后再试!");
            Asserts.assertExpression(false, ErrorCode.BAD_REQUEST, "重复请求，请稍后重试！");

        }
        return null;
    }

    // 获取key
    private String getIdeKey(String token, String requestURI) {
        return token + requestURI;
    }
}