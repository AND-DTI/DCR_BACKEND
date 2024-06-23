package com.dcr.api.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.dcr.api.model.as400.Accuser;

@Component
public class TokenService {

    @Value("${data.token_expire:280000000}")
    int TOKEN_EXPIRATION;

    @Value("${data.token_pass:SUPER_SECRET_WORD}")
    String TOKEN_PASS;

    public String gerarToken(Accuser user) {

        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
                .sign(Algorithm.HMAC512(TOKEN_PASS));

    }

    public TokenCST gerarToken2(Accuser user) {

        String tokenString = JWT.create()
                .withSubject(user.getUsername().trim())
                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION))
                .sign(Algorithm.HMAC512(TOKEN_PASS));

        Date dt = new Date(System.currentTimeMillis() + TOKEN_EXPIRATION);
        LocalDateTime ldt = LocalDateTime.ofInstant(dt.toInstant(), ZoneId.systemDefault());
        String expire = ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        TokenCST token = new TokenCST(tokenString, expire);

        return token;
    }

    public UsernamePasswordAuthenticationToken getAuthenticationToken(String token) {

        String usuario = null;

        usuario = JWT.require(Algorithm.HMAC512(TOKEN_PASS))
                .build()
                .verify(token)
                .getSubject();

        if (usuario == null) {
            return null;
        }

        return new UsernamePasswordAuthenticationToken(
                usuario, null, new ArrayList<>());

    }

}