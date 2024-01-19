package com.ferapp.api.configs.security;

import java.io.IOException;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.ferapp.api.service.TokenService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FilterToken extends OncePerRequestFilter {

    @Autowired
    TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));

        String atributo = request.getHeader("Authorization");
        if (atributo == null) {
            chain.doFilter(request, response);
            return;
        }

        if (!atributo.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        String token = atributo.replace("Bearer ", "");

        try {

            UsernamePasswordAuthenticationToken authenticationToken = tokenService.getAuthenticationToken(token);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);

        } catch (TokenExpiredException e) {

            response.setStatus(401);
            response.getOutputStream()
                    .println("Seu Token expirou. Realize novo login no sistema!\nDetalhe: " + e.getMessage());

        }

    }

}