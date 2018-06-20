package com.minshenglife.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

public class Md5PasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();

        return md5PasswordEncoder.encode(charSequence.toString()).equalsIgnoreCase(s);
    }
}
