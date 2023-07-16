package com.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


@Slf4j
@Aspect
@Component
public class ExecutionLogAspect {

    @Pointcut("execution(* com.demo.controller.*.hello(..)))")
    public void pointCut() {}

    @Before("pointCut()")
    public void doBefore() {
        log.info("before");
    }

    @After("pointCut()")
    public void doAfter() {
        log.info("after");
    }

    @AfterReturning("pointCut()")
    public void doAfterReturning() {
        log.info("after returning");
    }

    @AfterThrowing("pointCut()")
    public void doAfterThrowing() {
        log.info("after throwing");
    }

    @Around("pointCut()")
    public void doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("around before");
        joinPoint.proceed();
        log.info("around after");
    }
}
