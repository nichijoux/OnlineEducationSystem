package com.zh.oes.common.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zh.oes.common.security.entity.SecurityUser;
import com.zh.oes.common.security.entity.User;
import com.zh.oes.common.security.security.TokenManager;
import com.zh.oes.common.utils.ResponseUtil;
import com.zh.oes.common.utils.Result;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// 登录认证过滤器，继承UsernamePasswordAuthenticationFilter，对用户名密码进行登录校验
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {
    // 权限管理的工具
    private final AuthenticationManager authenticationManager;
    // token的管理工具
    private final TokenManager tokenManager;
    // redis缓存
    private final RedisTemplate<String, List<String>> redisTemplate;

    public TokenLoginFilter(AuthenticationManager authenticationManager, TokenManager tokenManager, RedisTemplate<String, List<String>> redisTemplate) {
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
        this.setPostOnly(false);
        // 验证的请求地址
        this.setRequiresAuthenticationRequestMatcher(
                new AntPathRequestMatcher("/admin/auth/login", "POST"));
    }

    // 根据用户名和密码进行验证
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
            throws AuthenticationException {
        try {
            User user = new ObjectMapper().readValue(req.getInputStream(), User.class);

            // 验证username和password，之后会进入service_acl模块下面的UserDetailServiceImpl.java中
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // 登录成功,则将user的信息放置在redis中
    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
                                            Authentication auth) {
        // 获取认证用户信息
        SecurityUser user = (SecurityUser) auth.getPrincipal();
        // 创建用户的token
        String token = tokenManager.createToken(user.getCurrentUserInfo().getUsername());
        // redis设置用户缓存; K,V为用户名和用户的权限列表
        redisTemplate.opsForValue().set(user.getCurrentUserInfo().getUsername(), user.getPermissionValueList());
        ResponseUtil.out(res, Result.success(token));
    }

    // 登录失败跳转的方法
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException e) {
        ResponseUtil.out(response, Result.failure().message("登录失败"));
    }
}
