package com.pipi.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pipi.security.pojo.domain.LoginUserInfo;
import com.pipi.security.pojo.domain.LoginUserInfo;
import com.pipi.security.pojo.dto.LoginDTO;
import com.pipi.security.pojo.dto.RegisterDTO;

public interface UserService extends IService<LoginUserInfo> {

    /**
     * 根据用户名查询用户信息
     */
    LoginUserInfo queryByUsername(String username);

    /**
     * 用户注册
     */
    boolean register(RegisterDTO params);

    /**
     * 用户登录
     * @param params 参数[用户名, 密码]
     * @return token
     */
    String login(LoginDTO params);
}
