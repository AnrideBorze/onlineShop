package com.sarakhman;

import org.apache.commons.codec.digest.DigestUtils;
import java.security.SecureRandom;

public class CookieHandler {

    public String getTokenValue() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] token = new byte[32];
        secureRandom.nextBytes(token);
        String userToken = DigestUtils.md5Hex(token);
        return userToken;
    }

}
