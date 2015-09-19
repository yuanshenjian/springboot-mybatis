package org.yood.springboot.mybatis.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EncryptUtilsTest {

    @Test
    public void testEncryptPassword() throws Exception {

        String pass = "000";
        String encryptPassword = EncryptUtils.encryptPassword(pass);
        assertEquals(60, encryptPassword.length());
    }
}