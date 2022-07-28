package com.zh.oes.common.security.security;

import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

// token管理类
@Component
public class TokenManager {
    // token持续时间 24 * 60 * 60 * 1000
    private final long tokenExpiration = 86400000;
    // 编码密钥
    private final String tokenSignKey = "nichijoux12138";

    // 创建token
    public String createToken(String username) {
        return Jwts.builder().setSubject(username)
                //设置过期时间
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                //用tokenSignKey编码
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP).compact();
    }

    // 从 token 中获取用户信息
    public String getUserFromToken(String token) {
        //用tokenSignKey解码
        return Jwts.parser().setSigningKey(tokenSignKey)
                .parseClaimsJws(token).getBody().getSubject();
    }
}
