package org.yood.springboot.mybatis.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public final class EncryptUtils {

    private EncryptUtils() {
    }

    public static String encryptPassword(String value) {
        return new BCryptPasswordEncoder().encode(value);
    }
}


