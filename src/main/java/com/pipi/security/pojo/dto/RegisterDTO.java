/**
 * @Time: 2025/1/3 14:28
 * @Author: guoxun
 * @File: RegisterDTO
 * @Description:
 */

package com.pipi.security.pojo.dto;


import lombok.Data;

@Data
public class RegisterDTO {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
