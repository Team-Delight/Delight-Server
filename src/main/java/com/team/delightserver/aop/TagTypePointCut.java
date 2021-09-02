package com.team.delightserver.aop;

import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @Created by Bloo
 * @Date: 2021/09/01
 */
@Slf4j
@Aspect
@Component
public class TagTypePointCut {

    @Pointcut("execution(* com.team.delightserver.service.ApiTagService.findMostFrequentTagByUserId())")
    private void cut() { }

    @Before ("cut()")
    public void before( JoinPoint joinPoint ) {
        MethodSignature methodSignature = ( MethodSignature ) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        log.info("함수명: {}", method.getName() );

        Object [] objects = joinPoint.getArgs();
        for (Object obj : objects) {
            log.info("Args: {}", obj);
        }
    }
}
