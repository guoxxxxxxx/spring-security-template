/**
 * @Time: 2025/1/6 10:32
 * @Author: guoxun
 * @File: RedisPrefix
 * @Description:
 */

package com.pipi.security.constant;

public enum RedisPrefix {

    LOGIN("login:");


    private final String prefix;

    RedisPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPrefix() {
        return this.prefix;
    }
}
