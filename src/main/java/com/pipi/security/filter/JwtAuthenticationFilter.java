/**
 * @Time: 2025/1/4 17:40
 * @Author: guoxun
 * @File: JwtAuthenticationFilter
 * @Description:
 */

package com.pipi.security.filter;

import com.alibaba.fastjson2.JSON;
import com.pipi.security.constant.CustomHttpStatus;
import com.pipi.security.exception.CommonException;
import com.pipi.security.constant.RedisPrefix;
import com.pipi.security.exception.InnerException;
import com.pipi.security.pojo.domain.LoginUserInfo;
import com.pipi.security.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // get token from header
        String token = request.getHeader("token");
        if (StringUtils.hasLength(token)) {
            String bearerToken = request.getHeader("Authorization");
            if (StringUtils.hasLength(bearerToken) && bearerToken.startsWith("Bearer ")) {
                token = bearerToken.substring(7);
            }
        }
        if (!StringUtils.hasLength(token)){
            // if token not in header, current user will be set anonymous.
            SecurityContextHolder.clearContext();
            filterChain.doFilter(request, response);
            return;
        }
        else {
            String username;
            Claims claims = JwtUtils.parseToken(token);
            username = claims.getSubject();
            String userDetailsString = stringRedisTemplate.opsForValue().get(RedisPrefix.LOGIN + username);
            if (!StringUtils.hasLength(userDetailsString)){
                logger.debug("current token maybe is expired.");
                throw new ExpiredJwtException(null, null, "the token is expired");
            }
            LoginUserInfo loginUserInfo = JSON.parseObject(userDetailsString, LoginUserInfo.class);
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(loginUserInfo
                    , loginUserInfo.getPassword(), loginUserInfo.getAuthorities()));
        }
        filterChain.doFilter(request, response);
    }
}
