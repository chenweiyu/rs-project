package com.xhm.rs.common.core;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * jwt工具类 引入 java jwt 工具包
 * https://blog.csdn.net/u014204541/article/details/103906208
 */
public class J {

    private static final long EXPIRE_TIME = 30 * 60 * 1000; // 有效期 30分钟
    private static final String TOKEN_SECRET = "f4e2e52034348f86b67cde581c0f9"; // 密钥盐

    /**
     * 签名生成
     * 
     * @param userId
     * @return
     */
    public static String sign(Long userId) throws Exception {
        String token = null;
        Date expiresAt = new Date(System.currentTimeMillis() + EXPIRE_TIME); // 有效时间
        token = JWT.create().withIssuer("auth0").withClaim("userId", userId) // 加入自己信息
                .withExpiresAt(expiresAt)
                // 使用了HMAC256加密算法。
                .sign(Algorithm.HMAC256(TOKEN_SECRET));

        return token;
    }

    /**
     * 签名验证
     * 
     * @param token
     * @return
     */
    public static Long verify(String token) throws Exception {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_SECRET)).withIssuer("auth0").build();
        DecodedJWT jwt = verifier.verify(token); // 验证
        Long uid = jwt.getClaim("userId").asLong();
        System.out.println("-------认证通过--------：");
        System.out.println("userId: " + uid);
        System.out.println("过期时间：      " + jwt.getExpiresAt());
        return uid;

    }

}
