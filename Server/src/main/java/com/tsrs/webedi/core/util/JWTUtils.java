package com.tsrs.webedi.core.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tsrs.webedi.common.persistence.model.User;
import com.tsrs.webedi.modular.system.service.IUserService;

import javax.annotation.Resource;
import java.util.Date;

public class JWTUtils {

    private static final long EXPIRE = 30 * 60 * 1000;
    private static final String SECRET = "!ad#12~";

    @Resource
    private IUserService userService;

    public static String getToken(User user) {
        JWTCreator.Builder builder = JWT.create();
        return builder.withClaim("account", user.getAccount())
                .withClaim("role", user.getUserType())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE))
                .sign(Algorithm.HMAC256(SECRET));
    }

    public static DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
    }

    public static String getAccount(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token).getClaim("account").asString();
    }
}

