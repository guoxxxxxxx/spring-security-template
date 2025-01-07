/**
 * @Time: 2024/12/20 16:20
 * @Author: guoxun
 * @File: UserServiceImpl
 * @Description:
 */

package com.pipi.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pipi.security.mapper.UserMapper;
import com.pipi.security.pojo.domain.LoginUserInfo;
import com.pipi.security.pojo.dto.RegisterDTO;
import com.pipi.security.service.UserService;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, LoginUserInfo> implements UserService {

    @Override
    public LoginUserInfo queryByUsername(String username) {
        return baseMapper.selectOne(new LambdaQueryWrapper<LoginUserInfo>()
                .eq(LoginUserInfo::getUsername, username));
    }

    @Override
    public boolean register(RegisterDTO params) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(16);
        String encryptionPassword = passwordEncoder.encode(params.getPassword());
        SecurityContext context = SecurityContextHolder.getContext();
        return true;
    }
}
