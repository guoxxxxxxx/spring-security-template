package com.pipi.security.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pipi.security.pojo.domain.AuthAuthoritiesInfo;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface AuthAuthoritiesMapper extends BaseMapper<AuthAuthoritiesInfo> {
}
