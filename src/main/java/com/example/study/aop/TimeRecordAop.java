package com.example.study.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class TimeRecordAop {
    // 패키지 기반으로 AOP를 적용하는 방식으로 구현
    // 서비스 레이어에서 실행된 메서드의 수행 시간을 로그로 찍어준다.
    @Around("execution(* com.example.study.service..*(..))")
    public Object executeTimeRecord(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        try {
            // joinPoint.proceed();는 다음 메서드를 진행하는 로직.
            return joinPoint.proceed();
        } finally {
            long endTime = System.currentTimeMillis();
            long execTime = endTime - startTime;

            // 메서드 수행 시간(ms) 및 정보
            log.info("execTime = " + execTime + "ms : " + joinPoint.toString());
        }
    }
}