/**
 * @Time: 2025/1/9 17:34
 * @Author: guoxun
 * @File: AuthAspect
 * @Description:
 */

package com.pipi.security.aop.aspect;


import com.pipi.security.aop.annotation.Auth;
import com.pipi.security.aop.annotation.Logger;
import com.pipi.security.constant.CustomHttpStatus;
import com.pipi.security.exception.CommonException;
import com.pipi.security.exception.InnerException;
import com.pipi.security.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.security.auth.login.CredentialException;
import java.lang.reflect.Method;
import java.util.List;

@Aspect
@Slf4j
@Component
public class AuthAspect {

    @Pointcut("@annotation(com.pipi.security.aop.annotation.Auth)")
    public void pointCut(){}


    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        // get token from header
        String token = request.getHeader("token");
        if (!StringUtils.hasLength(token)) {
            String bearerToken = request.getHeader("Authorization");
            if (StringUtils.hasLength(bearerToken) && bearerToken.startsWith("Bearer ")) {
                token = bearerToken.substring(7);
            }
        }
        if (!StringUtils.hasLength(token)){
            // if token not in header, the backend will send reLogin message to frontend.
            throw new InnerException(CustomHttpStatus.ERROR_LOGIN);
        }
        Claims claims = JwtUtils.parseToken(token);
        List<String> authoritiesList = claims.get("authorities", List.class);
        String roleName = claims.get("roleName", String.class);
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Auth needAuthority = method.getAnnotation(Auth.class);
        String value = null;
        String role = null;
        if (needAuthority != null){
            value = needAuthority.value();
            role = needAuthority.role();
        }
        if (authoritiesList.contains("SUPER") || authoritiesList.contains(value) || roleName.equals(role)) {
            log.debug("Permission verification successful");
        }
        else {
            throw new RuntimeException("当前用户无权访问");
        }
    }
}
