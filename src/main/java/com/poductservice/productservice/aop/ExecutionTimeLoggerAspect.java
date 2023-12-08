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

    @Around("@annotation(executionTimeLogger)")
    public Object executionTimeLogger(ProceedingJoinPoint proceedingJoinPoint, ExecutionTimeLogger executionTimeLogger){
        try{
            long start = System.currentTimeMillis();
            Object proceed = proceedingJoinPoint.proceed();
            long execution = System.currentTimeMillis() - start;

            log.info("{} method was executed in time {}(millisecond)", proceedingJoinPoint.getSignature().getName(), execution);
            return proceed;
        }catch(Throwable t){
            log.error("Error while calculating execution time for method {}", proceedingJoinPoint.getSignature(), t);
            return null;
        }
    }
}
