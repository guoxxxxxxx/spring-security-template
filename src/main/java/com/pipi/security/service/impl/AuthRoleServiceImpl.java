/**
 * @Time: 2025/1/9 17:54
 * @Author: guoxun
 * @File: AuthRoleServiceImpl
 * @Description:
 */

package com.pipi.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pipi.security.mapper.AuthRoleMapper;
import com.pipi.security.pojo.domain.AuthRoleInfo;
import com.pipi.security.service.AuthRoleService;
import org.springframework.stereotype.Service;



@Service
public class AuthRoleServiceImpl extends ServiceImpl<AuthRoleMapper, AuthRoleInfo> implements AuthRoleService {

}
