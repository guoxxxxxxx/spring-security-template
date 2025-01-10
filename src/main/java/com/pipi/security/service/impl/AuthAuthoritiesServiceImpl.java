/**
 * @Time: 2025/1/9 17:58
 * @Author: guoxun
 * @File: AuthAuthoritiesServiceImpl
 * @Description:
 */

package com.pipi.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pipi.security.mapper.AuthAuthoritiesMapper;
import com.pipi.security.pojo.domain.AuthAuthoritiesInfo;
import com.pipi.security.service.AuthAuthoritiesService;
import org.springframework.stereotype.Service;



@Service
public class AuthAuthoritiesServiceImpl extends ServiceImpl<AuthAuthoritiesMapper, AuthAuthoritiesInfo> implements AuthAuthoritiesService {
}
