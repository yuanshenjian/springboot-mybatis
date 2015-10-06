package org.yood.springboot.mybatis.web.controller;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.yood.springboot.mybatis.util.RSAUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

@RestController
public class EncryptionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EncryptionController.class);


    @RequestMapping(value = "/encryption-parameters",
                    method = RequestMethod.GET)
    public ResponseEntity<?> getEncryptionPublicKey(HttpServletRequest request) {
        KeyPair keyPair = RSAUtils.generateKeyPair(2048);
        PrivateKey privateKey = keyPair.getPrivate();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        request.getSession().setAttribute("_private_key", privateKey);

        String publicKeyString = RSAUtils.getBase64PublicKey(publicKey);
        LOGGER.info("Public key = {}", publicKeyString);
        LOGGER.info("Public key byte = {}", keyPair.getPublic().getEncoded());
        Map<String, Object> publicKeyMap = new HashMap<>();
        publicKeyMap.put("publicKey", keyPair.getPublic().getEncoded());
        return ResponseEntity.ok(publicKeyMap);
    }

    @RequestMapping(value = "/encryption-data",
                    method = RequestMethod.POST)
    public ResponseEntity<?> decrypt(HttpServletRequest request, @RequestBody String encryptedData) throws IOException {
        LOGGER.info("encrypt data = {}", encryptedData);
        String data = JSON.parseObject(encryptedData).getString("encryptedData");
        LOGGER.info("encrypt data = {}", data);
        PrivateKey privateKey = (PrivateKey) request.getSession().getAttribute("_private_key");
        LOGGER.info("Decrypt encryptedData = {}", RSAUtils.decrypt(data, privateKey));
        return new ResponseEntity(HttpStatus.OK);
    }
}
