/**
 * @Time: 2025/1/4 17:40
 * @Author: guoxun
 * @File: JwtTokenUtil
 * @Description:
 */

package com.pipi.security.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.UUID;


@Slf4j
public class JwtUtils {

    private static final String SECRET_KEY = "IJISDJIOSJIDJAIOJFIOASJDIOJASIODJIAO";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7L;
//    private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    /**
     * 创建token使用默认的过期时间
     * @param username 用户名
     * @param claims 数据
     * @return token
     */
    public static String createToken(String username, Map<String, Object> claims){
        return createToken(username, claims, EXPIRATION_TIME);
    }


    /**
     * token 生成
     * @param username 用户名
     * @param claims 数据
     * @param expirationTime 过期时间
     * @return token
     */
    public static String createToken(String username, Map<String, Object> claims, long expirationTime) {
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.id(UUID.randomUUID().toString())
                .issuer("system")
                .claims(claims)
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(expirationTime + new Date().getTime()))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)));
        jwtBuilder.header()
                .add("typ", "JWT")
                .add("alg", "HS256");
        return jwtBuilder.compact();
    }


    /**
     * 解析token
     * @param token token
     * @return token Claims
     */
    public static Claims parseToken(String token) {
        try {
            Jws<Claims> jws = Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseSignedClaims(token);
            return jws.getPayload();
        } catch (Exception e) {
            if (e instanceof ExpiredJwtException){
                log.info("Expired JWT token");
                throw new RuntimeException("Expired JWT token");
            }
            else if (e instanceof JwtException){
                log.info("Invalid JWT token");
                throw new RuntimeException("Invalid JWT token");
            }
            log.error("Token parsing error : {}", String.valueOf(e));
            throw new RuntimeException(e);
        }
    }
}
