package com.wyj.e_commerce_management_system.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    // 从配置文件读取JWT密钥和过期时间
    @Value("${jwt.secret:your-secret-key-1234567890abcdef}")  // 密钥至少32位
    private String secret;

    @Value("${jwt.expire:86400000}")  // 过期时间：24小时（毫秒）
    private long expire;

    // 生成密钥（HS256算法需要）
    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // 生成JWT令牌（传入手机号作为唯一标识）
    public String generateToken(String phone) {
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + expire);

        return Jwts.builder()
                .setSubject(phone)  // 主题：存储手机号
                .setIssuedAt(now)   // 签发时间
                .setExpiration(expireDate)  // 过期时间
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)  // 签名算法
                .compact();
    }

    // 解析令牌，获取手机号
    public String getPhoneFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();  // 返回主题（手机号）
    }

    // 验证令牌是否有效（未过期+签名正确）
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getSecretKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;  // 令牌无效（过期/签名错误）
        }
    }
}
