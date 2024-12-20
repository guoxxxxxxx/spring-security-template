/**
 * @Time: 2024/12/20 16:46
 * @Author: guoxun
 * @File: AuthAuthoritiesInfo
 * @Description:
 */

package com.pipi.security.pojo.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthAuthoritiesInfo {

    /**
     * 主键
     */
    private Long id;

    /**
     * 权限名称
     */
    private String name;
}
