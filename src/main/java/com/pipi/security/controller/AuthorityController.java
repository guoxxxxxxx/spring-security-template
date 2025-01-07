/**
 * @Time: 2025/1/3 14:26
 * @Author: guoxun
 * @File: AuthorityController
 * @Description:
 */

package com.pipi.security.controller;


import com.pipi.security.aop.annotation.Logger;
import com.pipi.security.common.CommonResult;
import com.pipi.security.pojo.dto.RegisterDTO;
import com.pipi.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthorityController {

    @Autowired
    UserService userService;


    @GetMapping("/test")
    @Logger("测试")
    public CommonResult test(){
        return new CommonResult().success().message("测试成功！");
    }


    @PostMapping("/register")
    @Logger("注册")
    public CommonResult register(@RequestBody RegisterDTO params){
        boolean status = userService.register(params);
        return new CommonResult().success().message("注册成功").data(status);
    }
}
