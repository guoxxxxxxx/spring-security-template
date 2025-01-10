/**
 * @Time: 2024/12/20 16:09
 * @Author: guoxun
 * @File: User
 * @Description:
 */

package com.pipi.security.pojo.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("tb_auth_user")
public class LoginUserInfo implements UserDetails, CredentialsContainer {

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户名
     */
    @Getter(AccessLevel.NONE)
    private String username;

    /**
     * 密码
     */
    @Getter(AccessLevel.NONE)
    private String password;

    /**
     * 角色id
     */
    private Long roleId;

    /**
     * 账户是否未过期
     */
    @Getter(AccessLevel.NONE)
    private Boolean accountNonExpired;

    /**
     * 账户是否未锁定
     */
    @Getter(AccessLevel.NONE)
    private Boolean accountNonLocked;

    /**
     * 账户资质是否位未失效
     */
    @Getter(AccessLevel.NONE)
    private Boolean credentialsNonExpired;

    /**
     * 是否可用
     */
    @Getter(AccessLevel.NONE)
    private Boolean enabled;

    /**
     * 角色权限列表
     */
    @TableField(exist = false)
    @Getter(AccessLevel.NONE)
    private List<String> authorities;

    /**
     * 用户角色名称
     */
    @TableField(exist = false)
    private String roleName;


    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired == null ? true : accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked == null ? true : accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired == null ? true : credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled == null ? true : enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        if (!authorities.isEmpty()) {
            List<GrantedAuthority> authorityList = new ArrayList<>();
            for (String authority : authorities) {
                authorityList.add(new SimpleGrantedAuthority(authority));
            }
            return authorityList;
        }
        else
            return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }


    @Override
    public void eraseCredentials() {
        this.password = null;
    }
}
