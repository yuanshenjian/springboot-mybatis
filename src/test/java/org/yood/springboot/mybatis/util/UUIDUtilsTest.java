package org.yood.springboot.mybatis.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UUIDUtilsTest {

    @Test
    public void testCreateUUID() throws Exception {
        String uuid = UUIDUtils.createUUID();
        assertEquals(uuid.length(), 32);
    }
}