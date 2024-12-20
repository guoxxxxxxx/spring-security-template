package com.pipi.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pipi.security.pojo.domain.LoginUserInfo;
import com.pipi.security.pojo.domain.LoginUserInfo;

public interface UserService extends IService<LoginUserInfo> {

    /**
     * 根据用户名查询用户信息
     */
    LoginUserInfo queryByUsername(String username);
}
