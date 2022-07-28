package com.zh.oes.common.security.security;

import com.zh.oes.common.utils.MD5Util;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

// 密码处理,MD5加密
@Component
public class DefaultPasswordEncoder implements PasswordEncoder {
    // 把未进行加密的密码进行MD5加密
    @Override
    public String encode(CharSequence rawPassword) {
        return MD5Util.getMD5(rawPassword.toString());
    }

    // 将未加密密码和数据库中的密码进行比对
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(MD5Util.getMD5(rawPassword.toString()));
    }
}