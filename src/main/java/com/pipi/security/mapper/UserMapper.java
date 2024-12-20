/**
 * @Time: 2024/12/20 16:21
 * @Author: guoxun
 * @File: UserMapper
 * @Description:
 */

package com.pipi.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pipi.security.pojo.domain.LoginUserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<LoginUserInfo> {
}
