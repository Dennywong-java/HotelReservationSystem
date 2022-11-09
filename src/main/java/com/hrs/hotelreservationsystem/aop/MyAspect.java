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
    public void pointCut() {

    }

    @AfterThrowing(pointcut = "pointCut()", throwing = "myException")
    public void doHandlerException(JoinPoint joinPoint, Exception myException) {
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        logger.error(String.format("AfterThrowing: Method %s is throwing errors: %s", methodName, myException.getMessage()));
    }


    public static void main(String[] args) {
        String s = "abc";
        String s2= s + "sdds";
        System.out.println(s2);
        String temp ="";
        for (int i = s.length() - 1; i >= 0; i--) {
            System.out.println(s.charAt(i));
            temp += s.charAt(i);
        }
        System.out.println(temp);
    }


}
