package org.yood.springboot.mybatis.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public final class EncryptUtils {

    private static final String METHOD_MD5 = "MD5";
    private static final String METHOD_SHA = "SHA";

    private EncryptUtils() {
    }

    public static String encryptPassword(String value) {
        return new BCryptPasswordEncoder().encode(value);
    }
}


