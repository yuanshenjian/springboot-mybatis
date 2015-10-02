package org.yood.springboot.mybatis.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import java.security.*;

public class RSAUtils {

    private static final String ALGORITHM = "RSA";

    public static KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("Failed to generate key pair!", e);
        }
    }

    public static String getPublicKey(PublicKey key) {
        return Base64.encodeBase64String(key.getEncoded());
    }

    public static byte[] encrypt(String data, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return cipher.doFinal(data.getBytes());
        } catch (Exception e) {
            throw new IllegalArgumentException("Encrypt failed!", e);
        }
    }

    public static String decrypt(byte[] data, PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return new String(cipher.doFinal(data));
        } catch (Exception e) {
            throw new IllegalArgumentException("Decrypt failed!", e);
        }
    }
}
