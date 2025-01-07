/**
@Time: 2025/1/7 17:18
@Author: guoxun
@File: AuthorityControllerTest
@Description: 
*/

package com.pipi.security.controller;

import com.pipi.security.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;


@SpringBootTest
public class AuthorityControllerTest {

    @Test
    public void getBCryptPassword() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode("123456");
        System.out.println(encode);
    }


    /**
     * 测试token生成
     */
    @Test
    public void testTokenGenerator() {
        Map<String, Object> map = new HashMap<>();
        map.put("username", "pipi");
        map.put("password", "123456");
        String token = JwtUtils.createToken(map.get("username").toString(), map);
        System.out.println(token);
    }
}
