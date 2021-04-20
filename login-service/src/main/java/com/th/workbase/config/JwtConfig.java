package com.th.workbase.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Date 2021-04-16-15:50
 * @Author tangJ
 * @Description
 * @Version 1.0
 */
@Component
@Slf4j
public class JwtConfig {

    @Autowired
    private Environment env;

    /*
     * 根据身份ID标识，生成Token
     */
    public String getToken(String identityId) {
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + Integer.parseInt(env.getProperty("config.jwt.expire")) * 1000);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(identityId)
                .setIssuedAt(nowDate)
                .setExpiration(null)
                .signWith(SignatureAlgorithm.HS512, env.getProperty("config.jwt.secret"))
                .compact();
    }

    /*
     * 获取 Token 中注册信息
     */
    public Claims getTokenClaim(String token) {
        try {
            return Jwts.parser().setSigningKey(env.getProperty("config.jwt.secret")).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            log.error("token:" + token + "|error:" + e.toString());
            return null;
        }
    }

    /*
     * Token 是否过期验证
     */
    public boolean isTokenExpired(Date expirationTime) {
        return expirationTime.before(new Date());
    }

    public String getHeader() {
        return env.getProperty("config.jwt.header");
    }
}
