/**
 * @Time: 2024/12/20 16:20
 * @Author: guoxun
 * @File: UserServiceImpl
 * @Description:
 */

package com.pipi.security.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pipi.security.constant.RedisPrefix;
import com.pipi.security.exception.CommonException;
import com.pipi.security.mapper.UserMapper;
import com.pipi.security.pojo.domain.AuthAuthoritiesInfo;
import com.pipi.security.pojo.domain.AuthRoleInfo;
import com.pipi.security.pojo.domain.LoginUserInfo;
import com.pipi.security.pojo.dto.LoginDTO;
import com.pipi.security.pojo.dto.RegisterDTO;
import com.pipi.security.service.AuthAuthoritiesService;
import com.pipi.security.service.AuthRoleService;
import com.pipi.security.service.UserService;
import com.pipi.security.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, LoginUserInfo> implements UserService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    AuthRoleService authRoleService;

    @Autowired
    AuthAuthoritiesService authAuthoritiesService;


    @Override
    public LoginUserInfo queryByUsername(String username) {
        return baseMapper.selectOne(new LambdaQueryWrapper<LoginUserInfo>()
                .eq(LoginUserInfo::getUsername, username));
    }


    @Override
    public boolean register(RegisterDTO params) {
        if (baseMapper.selectOne(new LambdaQueryWrapper<LoginUserInfo>()
                .eq(LoginUserInfo::getUsername, params.getUsername())) != null) {
            throw new CommonException("当前用户名已经注册");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(9);
        String encryptionPassword = passwordEncoder.encode(params.getPassword());
        LoginUserInfo loginUserInfo = new LoginUserInfo();
        loginUserInfo.setUsername(params.getUsername());
        loginUserInfo.setPassword(encryptionPassword);
        int insert = baseMapper.insert(loginUserInfo);
        return insert == 1;
    }


    @Override
    public String login(LoginDTO params) {
        LoginUserInfo loginUserInfo = baseMapper.selectOne(new LambdaQueryWrapper<LoginUserInfo>()
                .eq(LoginUserInfo::getUsername, params.getUsername()));
        if (loginUserInfo != null) {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            boolean matches = bCryptPasswordEncoder.matches(params.getPassword(), loginUserInfo.getPassword());
            if (matches) {

                AuthRoleInfo roleInfo = authRoleService.getById(loginUserInfo.getRoleId());
                loginUserInfo.setRoleName(roleInfo.getName());

                List<String> authoritiesIdList = Arrays.asList(roleInfo.getAuthorities().split(","));
                List<AuthAuthoritiesInfo> authoritiesObjList = authAuthoritiesService.list(new LambdaQueryWrapper<AuthAuthoritiesInfo>()
                        .in(AuthAuthoritiesInfo::getId, authoritiesIdList));
                List<String> authoritiesList = new ArrayList<>();
                for(AuthAuthoritiesInfo e : authoritiesObjList) {
                    authoritiesList.add(e.getName());
                }
                loginUserInfo.setAuthorities(authoritiesList);
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> map = objectMapper.convertValue(loginUserInfo, Map.class);
                map.put("authorities", authoritiesList);
                String token = JwtUtils.createToken(loginUserInfo.getUsername(), map);
                stringRedisTemplate.opsForValue().set(RedisPrefix.LOGIN_TOKEN + loginUserInfo.getUsername(), token,
                        JwtUtils.EXPIRATION_TIME, TimeUnit.MILLISECONDS);
                return token;
            }
        }
        else {
            throw new CommonException("当前用户未注册");
        }
        throw new CommonException("用户名或密码错误");
    }


    @Override
    public Map<String, Object> parseToken(String token) {
        Claims claims = JwtUtils.parseToken(token);
        Map<String, Object> claimsMap = new HashMap<>(claims);
        claimsMap.remove("password");
        return claimsMap;
    }
}
