package org.yood.springboot.mybatis.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public final class UUIDUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(UUIDUtils.class);

    private UUIDUtils() {
    }

    public static String createUUID() {
        String uuid = UUID.randomUUID().toString();
        LOGGER.info("Random uuid = {}", uuid);
        return uuid.replace("-", "");
    }
}
