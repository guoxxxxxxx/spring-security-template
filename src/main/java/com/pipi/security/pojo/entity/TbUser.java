/**
 * @Time: 2024/12/20 16:03
 * @Author: guoxun
 * @File: TbUser
 * @Description:
 */

package com.pipi.security.pojo.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tb_auth_user")
@Data
public class TbUser {

    /**
     * 主键
     */
    @Id
    @TableId(type = IdType.AUTO, value = "id")
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "int8 AUTO_INCREMENT")
    private Long id;

    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 角色
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 账户是否没过期
     */
    @Column(name = "accountNonExpired", columnDefinition = "bool default 1")
    private Boolean accountNonExpired;

    /**
     * 账户是否没被锁定
     */
    @Column(name = "accountNonLocked", columnDefinition = "bool default 1")
    private Boolean accountNonLocked;

    /**
     * 资格是否过期
     */
    @Column(name = "credentialsNonExpired", columnDefinition = "bool default 1")
    private Boolean credentialsNonExpired;

    /**
     * 是否可用
     */
    @Column(name = "enable", columnDefinition = "bool default 1")
    private Boolean enable;

    /**
     * 删除位
     */
    @Column(name = "deleted", columnDefinition = "bool default 0")
    private Boolean deleted;
}
