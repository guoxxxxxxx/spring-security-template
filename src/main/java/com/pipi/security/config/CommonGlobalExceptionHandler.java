/**
 * @Time: 2024/8/30 16:53
 * @Author: guoxun
 * @File: GlobalExceptionHandler
 * @Description:
 */

package com.pipi.security.config;

import com.pipi.security.common.CommonException;
import com.pipi.security.common.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class CommonGlobalExceptionHandler {


    /**
     * 全局异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e){
        log.error(e.toString());
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", new Date());
        response.put("status", 500);
        response.put("error", "Internal Server Error");
        response.put("message", e.toString());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(CommonException.class)
    public CommonResult handleCommonException(CommonException e){
        return new CommonResult().status(456).message(e.getMessage());
    }

}
