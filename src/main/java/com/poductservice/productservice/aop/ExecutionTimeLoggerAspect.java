package com.poductservice.productservice.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ExecutionTimeLoggerAspect {

    @Around("@annotation(ExecutionTimeLogger)")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long execution = System.currentTimeMillis() - start;

        log.info("{} method was executed in time {}(millisecond)", joinPoint.getSignature().getName(), execution);
        return proceed;
    }

    /***
     * Aspect : Class in which we define Pointcuts and Advices. eg : ExecutionTimeLoggerAspect
     * Advice : Methods are advices eg : executionTimeLogger()
     * Pointcut : expression that defines at what joinPoints a given advice should be applied
     * JoinPoint : is a point in the execution flow of a method where an Aspect can be plugged in.
     */
}
