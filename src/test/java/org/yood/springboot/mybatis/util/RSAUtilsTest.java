package org.yood.springboot.mybatis.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class RSAUtilsTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(RSAUtilsTest.class);

    @Test
    public void testGenerateKeyPair() throws Exception {
        KeyPair keyPair = RSAUtils.generateKeyPair();
        assertNotNull(keyPair);
    }

    @Test
    public void testEncrypt() throws Exception {
        KeyPair keyPair = RSAUtils.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        LOGGER.info("Public Key = {}, Private Key = {}", publicKey, privateKey);

        String encryptData = "中华人民共和国";
        LOGGER.info("Encrypt data = {}", encryptData);
        String encryptedData = RSAUtils.encryptAsString(encryptData, publicKey);
        String decryptData = RSAUtils.decrypt(encryptedData, privateKey);
        LOGGER.info("Decrypt data = {}", decryptData);
        assertEquals(encryptData, decryptData);
    }

    @Test
    public void testDecryptForByteArray() throws Exception {
        testEncrypt();
    }

    @Test
    public void testDecryptForString() throws Exception {

    }

    @Test
    public void testGetPublicKey() throws Exception {

    }

    @Test
    public void testGetPublicKey1() throws Exception {

    }

    @Test
    public void testGetPrivateKey() throws Exception {

    }

    @Test
    public void testGetPrivateKey1() throws Exception {

    }

    @Test
    public void testEncryptAsByteArray() throws Exception {

    }

    @Test
    public void testDecrypt1() throws Exception {

    }

    @Test
    public void testEncryptAsString() throws Exception {

    }
}