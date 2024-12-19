/**
 * @Time: 2024/8/28 15:12
 * @Author: guoxun
 * @File: LogAspect
 * @Description:
 */

package com.pipi.security.aop.aspect;

import com.alibaba.fastjson2.JSON;
import com.pipi.security.aop.annotation.Logger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;

@Aspect
@Slf4j
@Component
public class LogAspect {

    @Pointcut("@annotation(com.pipi.security.aop.annotation.Logger)")
    public void pointCut(){}

    @Around("pointCut()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object proceed = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();
        log.info("执行时间为: {} ms", endTime - startTime);
        return proceed;
    }


    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint) throws Exception{
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("请求 --> url: {}, method: {}, ip: {}, class_method: {}, args: {}",
                request.getRequestURI(), request.getMethod(), request.getRemoteAddr(), joinPoint.getSignature().getDeclaringType().getName()
                        + "." + joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "pointCut()", returning = "result")
    public void doAfter(JoinPoint joinPoint, Object result){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Logger logger = method.getAnnotation(Logger.class);
        String value = null;
        if (logger != null){
            value = logger.value();
        }
        log.info("{}方法返回值为: {}", value, JSON.toJSONString(result));
    }

}