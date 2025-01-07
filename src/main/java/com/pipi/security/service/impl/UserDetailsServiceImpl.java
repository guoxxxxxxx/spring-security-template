/**
 * @Time: 2024/12/20 15:11
 * @Author: guoxun
 * @File: UserDetailsServiceImpl
 * @Description:
 */

package com.pipi.security.service.impl;

import com.pipi.security.exception.CommonException;
import com.pipi.security.pojo.domain.LoginUserInfo;
import com.pipi.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUserInfo loginUser = userService.queryByUsername(username);
        if (loginUser == null) {
            throw new CommonException("用户名或密码错误");
        }
        return loginUser;
    }
}
