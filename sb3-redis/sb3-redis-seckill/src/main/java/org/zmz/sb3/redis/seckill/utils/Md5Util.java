package org.zmz.sb3.redis.seckill.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

@Slf4j
public final class Md5Util {

    public static final String salt = "a1b2c3d4";

    private Md5Util() {
    }

    public static String md5(String source) {
        String mixSource = salt.charAt(0) + salt.charAt(2) + source + salt.charAt(6) + salt.charAt(5);
        return DigestUtils.md5DigestAsHex(mixSource.getBytes(StandardCharsets.UTF_8));
    }

    public static String md5(String source, String dbRandomSalt) {
        String firstMd5 = md5(source);
        String mixSource = dbRandomSalt.charAt(1) + dbRandomSalt.charAt(3) + firstMd5 + dbRandomSalt.charAt(1) + dbRandomSalt.charAt(4);
        return DigestUtils.md5DigestAsHex(mixSource.getBytes(StandardCharsets.UTF_8));
    }

    public static void main(String[] args) {
        String source = "123456";
        String firstMd5 = Md5Util.md5(source);
        log.info("{}", firstMd5);
        log.info("====================================");
        String password = md5(firstMd5, "1a2b3c4d");
        log.info("{}", password);
    }
}
