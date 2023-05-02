package ru.mirea.task24;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@org.aspectj.lang.annotation.Aspect
public class Aspect {

    private long start;

    @Before("allLog()")
    public void startMethod(JoinPoint joinPoint) {
        this.start = System.nanoTime();
        log.info("Method: " + joinPoint.getSignature() + " started");
    }

    @After("allLog()")
    public void endMethod(JoinPoint joinPoint) {
        log.info("Method " + joinPoint.getSignature() + " ended in " +
                ((System.nanoTime() - start) / 1000000000.f) + " ms");
    }

    @Pointcut("within(ru.mirea.task24.services.*)")
    public void allLog() {
    }
}
