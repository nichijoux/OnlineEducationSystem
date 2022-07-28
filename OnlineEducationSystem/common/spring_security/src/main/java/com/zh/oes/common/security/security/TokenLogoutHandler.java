package com.zh.oes.common.security.security;

import com.zh.oes.common.utils.ResponseUtil;
import com.zh.oes.common.utils.Result;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

// 登出业务逻辑类
public class TokenLogoutHandler implements LogoutHandler {

    private final TokenManager tokenManager;
    private final RedisTemplate<String, List<String>> redisTemplate;

    public TokenLogoutHandler(TokenManager tokenManager, RedisTemplate<String, List<String>> redisTemplate) {
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        // 从header中获取token
        String token = request.getHeader("token");
        if (token != null) {
            // 清空当前用户缓存中的权限数据
            String userName = tokenManager.getUserFromToken(token);
            redisTemplate.delete(userName);
        }
        ResponseUtil.out(response, Result.success());
    }
}