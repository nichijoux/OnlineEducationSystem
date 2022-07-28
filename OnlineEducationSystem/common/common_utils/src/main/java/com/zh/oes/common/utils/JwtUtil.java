package com.zh.oes.common.utils;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * jwt工具类
 */
public class JwtUtil {

    public static final long EXPIRE = 1000 * 60 * 60 * 24;
    //密钥
    public static final String APP_SECRET = "nichijoux-8BDbRigCklUDaYHao6pZFfWTs2jZHeng";

    /**
     * 生成会员的jwtToken的方法
     *
     * @param id       用户id
     * @param nickname 用户昵称
     * @return 根据id和nickname生成的token
     */
    public static String getUserJwtToken(String id, String nickname) {
        return Jwts.builder()
                //设置头信息
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setSubject("oes-user")
                //设置过期时间
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                //设置token主体部分
                .claim("id", id)
                .claim("nickname", nickname)
                //签名哈希
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();
    }

    /**
     * 判断token是否存在与有效
     *
     * @param jwtToken 待判断的token
     * @return jwtToken是否存在与有效
     */
    public static boolean checkUserToken(String jwtToken) {
        if (StringUtils.isEmpty(jwtToken)) {
            return false;
        }
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 判断token是否存在与有效
     *
     * @param request 请求头
     * @return jwtToken是否存在与有效
     */
    public static boolean checkUserToken(HttpServletRequest request) {
        try {
            String jwtToken = request.getHeader("token");
            if (StringUtils.isEmpty(jwtToken)) {
                return false;
            }
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 根据jwtToken获取会员id
     *
     * @param jwtToken 待解析的jwtToken
     * @return jwtToken中解析出的会员id
     */
    public static String getUserIdByJwtToken(String jwtToken) {
        if (StringUtils.isEmpty(jwtToken)) {
            return "";
        }
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (String) claims.get("id");
    }

    /**
     * 根据token获取会员id
     *
     * @param request 请求头
     * @return jwtToken中解析出的会员id
     */
    public static String getUserIdByJwtToken(HttpServletRequest request) {
        String jwtToken = request.getHeader("token");
        if (StringUtils.isEmpty(jwtToken)) {
            return "";
        }
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        Claims claims = claimsJws.getBody();
        return (String) claims.get("id");
    }
}