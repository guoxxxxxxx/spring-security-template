/**
 * @Time: 2025/1/6 10:32
 * @Author: guoxun
 * @File: RedisPrefix
 * @Description:
 */

package com.pipi.security.constant;

public enum RedisPrefix {

    LOGIN_TOKEN("login_token:");


    private final String prefix;

    RedisPrefix(String prefix) {
        this.prefix = prefix;
    }


    public String getPrefix() {
        return this.prefix;
    }


    @Override
    public String toString() {
        return this.prefix;
    }
}
