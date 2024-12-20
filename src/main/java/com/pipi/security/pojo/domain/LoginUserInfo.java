/**
 * @Time: 2024/12/20 16:09
 * @Author: guoxun
 * @File: User
 * @Description:
 */

package com.pipi.security.pojo.domain;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tb_auth_user")
public class LoginUserInfo implements UserDetails {

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 权限
     */
    private Long roleId;

    /**
     * 账户是否未过期
     */
    private Boolean accountNonExpired;

    /**
     * 账户是否未锁定
     */
    private Boolean accountNonLocked;

    /**
     * 账户资质是否位未失效
     */
    private Boolean credentialsNonExpired;

    /**
     * 是否可用
     */
    private Boolean enable;

    /**
     * 角色名称
     */
    private String roleName;

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enable;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        List<GrantedAuthority> role = new ArrayList<>();
        role.add(new SimpleGrantedAuthority(roleName));
        return role;
    }
}
