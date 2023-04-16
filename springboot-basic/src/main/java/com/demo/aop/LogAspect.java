package com.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class LogAspect {

    private final ThreadLocal<Long> start = new ThreadLocal<>();

    @Pointcut("execution(public * com.demo.aop.*.*(..)))")
    public void pointcut() {}

    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) {
        start.set(System.currentTimeMillis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info(Thread.currentThread().getName());
        log.info("before: {} {} {}", request.getRemoteAddr(), request.getMethod(), request.getRequestURI());
    }

    @AfterReturning(pointcut = "pointcut()", returning = "result")
    public void doAfterReturning(Object result) {
        log.info("after: {}", result);
        log.info("after: {}", System.currentTimeMillis() - start.get());
    }
}
