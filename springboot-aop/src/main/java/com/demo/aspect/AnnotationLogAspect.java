package com.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


@Slf4j
@Aspect
@Component
public class AnnotationLogAspect {

    @Pointcut("@annotation(com.demo.annotations.CheckPoint)")
    public void pointCut() {}

    @Before("pointCut()")
    public void doBefore() {
        log.info("before");
    }

    @After("pointCut()")
    public void doAfter() {
        log.info("after");
    }

    @AfterThrowing("pointCut()")
    public void doAfterThrowing() {
        log.info("after throwing");
    }

    @AfterReturning(value = "pointCut()", returning = "result")
    public void doAfterReturning(Object result) {
        log.info("{}", result);
        log.info("after returning");
    }

    //@Around("pointCut()")
    //public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
    //    log.info("around");
    //    Object result = joinPoint.proceed();
    //    log.info("{}", result);
    //    return result;
    //}
}
