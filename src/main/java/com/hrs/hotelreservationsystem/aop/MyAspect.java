package com.hrs.hotelreservationsystem.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class MyAspect {
    private Logger logger = LoggerFactory.getLogger("MyAspect Loggers");

    @Pointcut("execution(public * com.hrs..*(..))")
    public void pointCut(){

    }

    @AfterThrowing(pointcut = "pointCut()", throwing = "myException")
    public void doHandlerException(JoinPoint joinPoint, Exception myException){
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        logger.error(String.format("AfterThrowing: Method %s is throwing errors: %s", methodName, myException.getMessage()));
    }

}
