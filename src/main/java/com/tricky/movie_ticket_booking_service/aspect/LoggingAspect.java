package com.tricky.movie_ticket_booking_service.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(* com.tricky.movie_ticket_booking_service..*(..))")
    public void applicationMethods() {}

    @Before("applicationMethods()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Entering method: {} in class: {}", joinPoint.getSignature().getName(), joinPoint.getTarget().getClass().getSimpleName());
    }

    @After("applicationMethods()")
    public void logAfter(JoinPoint joinPoint) {
        log.info("Exiting method: {} in class: {}", joinPoint.getSignature().getName(), joinPoint.getTarget().getClass().getSimpleName());
    }

    @AfterThrowing(pointcut = "applicationMethods()", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        log.error("Method {} in class {} threw exception: {}", joinPoint.getSignature().getName(), joinPoint.getTarget().getClass().getSimpleName(), error.getMessage());
    }
}

