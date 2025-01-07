/**
 * @Time: 2025/1/7 15:52
 * @Author: guoxun
 * @File: CustomAuthenticationFailureHandler
 * @Description:
 */

package com.pipi.security.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;


@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().print(exception.getMessage());
        PrintWriter out = response.getWriter();
        out.write("{\"status\": 500, \"message\": \"" + exception.getMessage() + "\"}");
        out.flush();
        out.close();
    }
}
