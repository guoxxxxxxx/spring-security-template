/**
@Time: 2025/1/7 17:18
@Author: guoxun
@File: AuthorityControllerTest
@Description: 
*/

package com.pipi.security.controller;

import com.pipi.security.service.UserService;
import com.pipi.security.utils.JwtUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;


@SpringBootTest
public class AuthorityControllerTest {

    @Autowired
    UserService userService;

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


    @Test
    public void testParseJwtToken() {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2ZjJhMDQ1Yy01YWFjLTQyMjktODYzYy03MWZiODVhYTVmZDEiLCJpc3MiOiJzeXN0ZW0iLCJ1c2VybmFtZSI6InVzZXIiLCJwYXNzd29yZCI6IiQyYSQwOSRXSG9hcnBwZzgvVS5aL3ltLkNkZzd1YVZFaU1KVTFLMUJscXNwb3RVVWZ4cHM3YnA3N0NXVyIsImFjY291bnROb25FeHBpcmVkIjp0cnVlLCJhY2NvdW50Tm9uTG9ja2VkIjp0cnVlLCJjcmVkZW50aWFsc05vbkV4cGlyZWQiOnRydWUsImVuYWJsZWQiOnRydWUsInN1YiI6InVzZXIiLCJpYXQiOjE3MzYyOTc4ODAsImV4cCI6MTczNjkwMjY4MH0.HRdU19neGxT-0nngTuiB3nNA-9xJFf0ejs8Wco404BI";
        String s = userService.parseToken(token);
        System.out.println(s);
    }
}
