/**
 * @Time: 2024/8/30 16:53
 * @Author: guoxun
 * @File: GlobalExceptionHandler
 * @Description:
 */

package com.pipi.security.config;

import com.pipi.security.exception.CommonException;
import com.pipi.security.common.CommonResult;
import com.pipi.security.exception.InnerException;
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


    /**
     * 公共异常, 向用户展示message中的信息
     * @param e
     * @return
     */
    @ExceptionHandler(CommonException.class)
    public CommonResult handleCommonException(CommonException e){
        return new CommonResult().status(5000).message(e.getMessage());
    }


    /**
     * 内部意义, 不向用户展示，通知前端该进行什么样的操作
     * @param e InnerException
     * @return CommonResult
     */
    @ExceptionHandler(InnerException.class)
    public CommonResult handleInnerException(InnerException e){
        return new CommonResult().status(e.getCustomHttpStatus().getStatusCode()).message(e.getMessage());
    }

}
