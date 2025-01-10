/**
 * @Time: 2024/12/20 15:11
 * @Author: guoxun
 * @File: UserDetailsServiceImpl
 * @Description:
 */

package com.pipi.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.pipi.security.exception.CommonException;
import com.pipi.security.pojo.domain.AuthAuthoritiesInfo;
import com.pipi.security.pojo.domain.AuthRoleInfo;
import com.pipi.security.pojo.domain.LoginUserInfo;
import com.pipi.security.service.AuthAuthoritiesService;
import com.pipi.security.service.AuthRoleService;
import com.pipi.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthRoleService authRoleService;

    @Autowired
    private AuthAuthoritiesService authAuthoritiesService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LoginUserInfo loginUser = userService.queryByUsername(username);

        if (loginUser == null) {
            throw new CommonException("用户名或密码错误");
        }

        AuthRoleInfo roleInfo = authRoleService.getById(loginUser.getRoleId());
        loginUser.setRoleName(roleInfo.getName());

        List<String> authoritiesIdList = Arrays.asList(roleInfo.getAuthorities().split(","));
        List<AuthAuthoritiesInfo> authoritiesObjList = authAuthoritiesService.list(new LambdaQueryWrapper<AuthAuthoritiesInfo>()
                .in(AuthAuthoritiesInfo::getId, authoritiesIdList));
        List<String> authoritiesList = new ArrayList<>();
        for(AuthAuthoritiesInfo e : authoritiesObjList) {
            authoritiesList.add(e.getName());
        }
        loginUser.setAuthorities(authoritiesList);

        return loginUser;
    }
}
