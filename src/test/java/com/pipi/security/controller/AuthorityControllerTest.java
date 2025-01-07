/**
@Time: 2025/1/7 17:18
@Author: guoxun
@File: AuthorityControllerTest
@Description: 
*/

package com.pipi.security.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootTest
public class AuthorityControllerTest {

    @Test
    public void getBCryptPassword() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode("123456");
        System.out.println(encode);
    }
}
