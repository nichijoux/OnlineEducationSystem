package com.zh.oes.common.security.filter;

import com.zh.oes.common.security.security.TokenManager;
import com.zh.oes.common.utils.ResponseUtil;
import com.zh.oes.common.utils.Result;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


// 授权过滤
public class TokenAuthenticationFilter extends BasicAuthenticationFilter {
    private final TokenManager tokenManager;
    private final RedisTemplate<String, List<String>> redisTemplate;

    public TokenAuthenticationFilter(AuthenticationManager authManager, TokenManager tokenManager, RedisTemplate<String, List<String>> redisTemplate) {
        super(authManager);
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        // spring security加载service_auth模块上面，那就只会接收匹配断言*/auth/**的请求，并且service_auth模块上面都是/admin/auth开头的
        // 所以访问service_acl的每一个请求都会来到这里，不过if拦不住它
        if (!req.getRequestURI().contains("admin/auth")) {
            chain.doFilter(req, res);
            return;
        }
        // 获取当前认证成功用户权限信息
        UsernamePasswordAuthenticationToken authentication = null;
        try {
            authentication = getAuthentication(req);
        } catch (Exception e) {
            ResponseUtil.out(res, Result.failure());
        }
        // 如果有权限信息,放到权限上下文中
        if (authentication != null) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            ResponseUtil.out(res, Result.failure());
        }
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        // token置于header里
        String token = request.getHeader("token");
        if (token != null && !"".equals(token.trim())) {
            // 获取用户名称
            String userName = tokenManager.getUserFromToken(token);
            // 取出权限值列表之后再使用SimpleGrantedAuthority进行封装
            List<String> permissionValueList = redisTemplate.opsForValue().get(userName);
            if (permissionValueList == null) return null;
            // 权限列表信息,将数据库中String对象转化为GrantedAuthority对象
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            for (String permissionValue : permissionValueList) {
                if (StringUtils.isEmpty(permissionValue)) continue;
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(permissionValue);
                authorities.add(authority);
            }
            if (!StringUtils.isEmpty(userName)) {
                return new UsernamePasswordAuthenticationToken(userName, token, authorities);
            }
        }
        return null;
    }
}