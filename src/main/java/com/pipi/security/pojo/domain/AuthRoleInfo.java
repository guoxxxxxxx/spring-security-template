/**
 * @Time: 2024/12/20 16:36
 * @Author: guoxun
 * @File: AuthRoleInfo
 * @Description:
 */

package com.pipi.security.pojo.domain;

import jakarta.persistence.Table;
import lombok.Data;


@Data
@Table(name = "tb_auth_role")
public class AuthRoleInfo {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 用户角色名称
     */
    private String name;

    /**
     * 所拥有的权限
     */
    private String authorities;
}
