package org.yood.springboot.mybatis.web.controller;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.yood.springboot.mybatis.BasicMockMvcTest;
import org.yood.springboot.mybatis.util.JSONUtils;
import org.yood.springboot.mybatis.util.RSAUtils;

import javax.servlet.http.HttpSession;
import java.security.KeyPair;
import java.security.PrivateKey;

import static junit.framework.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EncryptionControllerTest extends BasicMockMvcTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(EncryptionControllerTest.class);

    @InjectMocks
    private EncryptionController encryptionController;

    @Override
    protected Object injectController() {
        return encryptionController;
    }

    @Test
    public void testGetEncryptionPublicKey() throws Exception {
        HttpSession session = mockGet("/encryption-parameters", MediaType.APPLICATION_JSON).andExpect(status().isOk())
                .andExpect(jsonPath("$.publicKey").exists())
                .andDo(print())
                .andReturn()
                .getRequest()
                .getSession();
        String privateKey = session.getAttribute("_private_key").toString();
        assertNotNull(privateKey);
    }

    @Test
    public void testDecrypt() throws Exception {
        KeyPair keyPair = RSAUtils.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        LOGGER.info("public key = {}", Base64.encodeBase64String(keyPair.getPublic().getEncoded()));
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("_private_key", privateKey);
        String encryptAsString = RSAUtils.encryptAsString("hello world", keyPair.getPublic());
        LOGGER.info("Encrypt data = {}", encryptAsString);
        ImmutableMap<String, String> content = ImmutableMap.of("encryptedData", encryptAsString);
        mockPost("/encryption-data",MediaType.APPLICATION_JSON, JSONUtils.toJSONString(content),session);
//        mockMvc.perform(post("/encryption-data").contentType(MediaType.APPLICATION_JSON)
//                                .session(session)
//                                .content(JSONUtils.toJSONString(content))).andExpect(status().isOk());
    }
}