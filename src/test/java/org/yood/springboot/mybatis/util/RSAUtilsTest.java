package org.yood.springboot.mybatis.util;

import org.junit.BeforeClass;
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

    private static KeyPair keyPair;
    private static PublicKey publicKey;
    private static PrivateKey privateKey;

    @BeforeClass
    public static void setup() {
        keyPair = RSAUtils.generateKeyPair();
        publicKey = keyPair.getPublic();
        privateKey = keyPair.getPrivate();
        LOGGER.info("Public Key = {}, Private Key = {}", publicKey, privateKey);
    }

    @Test
    public void testGenerateKeyPair() throws Exception {
        assertNotNull(keyPair);
        assertNotNull(publicKey);
        assertNotNull(privateKey);
    }

    @Test
    public void testGetPublicKeyStr() throws Exception {
        String publicKeyString = RSAUtils.getPublicKeyStr(RSAUtilsTest.publicKey);
        assertEquals(publicKey, RSAUtils.getPublicKey(publicKeyString));
    }

    @Test
    public void testGetPublicKey() throws Exception {
        testGetPublicKeyStr();
    }

    @Test
    public void testGetPrivateKeyStr() throws Exception {
        String privateKeyString = RSAUtils.getPrivateKeyStr(RSAUtilsTest.privateKey);
        assertEquals(privateKey, RSAUtils.getPrivateKey(privateKeyString));
    }

    @Test
    public void testGetPrivateKey() throws Exception {
        testGetPrivateKeyStr();
    }

    @Test
    public void testEncryptAsByteArray() throws Exception {
        String encryptData = "中华人民共和国";
        LOGGER.info("Encrypt data = {}", encryptData);
        byte[] encryptedData = RSAUtils.encryptAsByteArray(encryptData, publicKey);
        String decryptData = RSAUtils.decrypt(encryptedData, privateKey);
        LOGGER.info("Decrypt data = {}", decryptData);
        assertEquals(encryptData, decryptData);
    }

    @Test
    public void testEncryptAsString() throws Exception {
        String encryptData = "中华人民共和国";
        LOGGER.info("Encrypt data = {}", encryptData);
        String encryptedData = RSAUtils.encryptAsString(encryptData, publicKey);
        String decryptData = RSAUtils.decrypt(encryptedData, privateKey);
        LOGGER.info("Decrypt data = {}", decryptData);
        assertEquals(encryptData, decryptData);
    }

    @Test
    public void testDecryptForByteArray() throws Exception {
        testEncryptAsByteArray();
    }

    @Test
    public void testDecryptForString() throws Exception {
        testEncryptAsString();
    }

    @Test
    public void testEncryptAsString1() throws Exception {
        String encryptData = "中华人民共和国";
        LOGGER.info("Encrypt data = {}", encryptData);
        String encryptedData = RSAUtils.encryptAsString(encryptData, RSAUtils.getPublicKeyStr(publicKey));
        String decryptData = RSAUtils.decrypt(encryptedData, RSAUtils.getPrivateKeyStr(privateKey));
        LOGGER.info("Decrypt data = {}", decryptData);
        assertEquals(encryptData, decryptData);
    }

    @Test
    public void testEncryptAsByteArray1() throws Exception {
        String encryptData = "中华人民共和国";
        LOGGER.info("Encrypt data = {}", encryptData);
        byte[] encryptedData = RSAUtils.encryptAsByteArray(encryptData, RSAUtils.getPublicKeyStr(publicKey));
        String decryptData = RSAUtils.decrypt(encryptedData, RSAUtils.getPrivateKeyStr(privateKey));
        LOGGER.info("Decrypt data = {}", decryptData);
        assertEquals(encryptData, decryptData);
    }
}