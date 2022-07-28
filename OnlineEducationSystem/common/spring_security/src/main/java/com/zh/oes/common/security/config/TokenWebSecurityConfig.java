package com.zh.oes.common.security.config;

import com.zh.oes.common.security.filter.TokenAuthenticationFilter;
import com.zh.oes.common.security.filter.TokenLoginFilter;
import com.zh.oes.common.security.security.DefaultPasswordEncoder;
import com.zh.oes.common.security.security.TokenLogoutHandler;
import com.zh.oes.common.security.security.TokenManager;
import com.zh.oes.common.security.security.UnauthorizedEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


// 核心配置类
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class TokenWebSecurityConfig extends WebSecurityConfigurerAdapter {

    // 自定义查询数据库的类
    private final UserDetailsService userDetailsService;
    // token操作工具类
    private final TokenManager tokenManager;
    // 密码处理类
    private final DefaultPasswordEncoder defaultPasswordEncoder;
    // redis处理
    private final RedisTemplate<String, List<String>> redisTemplate;

    @Autowired
    public TokenWebSecurityConfig(UserDetailsService userDetailsService, DefaultPasswordEncoder defaultPasswordEncoder,
                                  TokenManager tokenManager, RedisTemplate<String, List<String>> redisTemplate) {
        this.userDetailsService = userDetailsService;
        this.defaultPasswordEncoder = defaultPasswordEncoder;
        this.tokenManager = tokenManager;
        this.redisTemplate = redisTemplate;
    }

    // 配置设置
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                .authenticationEntryPoint(new UnauthorizedEntryPoint())//没有权限时应该调用的类
                .and().csrf().disable()
                .authorizeRequests()
                // 所有请求都需要登录(唯一鉴权)
                .anyRequest().authenticated()
                .and().logout().logoutUrl("/admin/auth/index/logout")//设置退出地址
                .addLogoutHandler(new TokenLogoutHandler(tokenManager, redisTemplate)).and()//退出时调用的函数
                .addFilter(new TokenLoginFilter(authenticationManager(), tokenManager, redisTemplate))
                .addFilter(new TokenAuthenticationFilter(authenticationManager(), tokenManager, redisTemplate))
                .httpBasic();

        // 基于token,因此不需要session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    // 密码处理
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(defaultPasswordEncoder);
    }

    // 配置哪些请求不拦截
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/api/**",
                "/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**"
        );
    }
}