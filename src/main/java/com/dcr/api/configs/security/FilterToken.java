package com.dcr.api.configs.security;
import java.io.IOException;
import java.util.TimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.dcr.api.service.TokenService;
import com.dcr.api.service.as400.DcrcorsrqService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;




@Component
public class FilterToken extends OncePerRequestFilter {


    @Autowired
    TokenService tokenService;

    @Autowired 
    DcrcorsrqService corsService;
    

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));

        String atributo = request.getHeader("Authorization");
        String path = request.getRequestURL().toString();
        if(path.contains("/api/auth") || path.contains("/api/health-check") || path.contains("/swagger-ui") || path.contains("/v3/api-docs")) {
        	chain.doFilter(request, response);
            return;
        }
        
        if (atributo == null) {
        	 response.setStatus(401);
             response.getOutputStream()
                     .println("Informar o token, realize login no sistema!");
             return;
        }

        String token = atributo.replace("Bearer ", "");

        try {
        	
            //String clientIp = Auxiliar.getClientIP(request);
            //String rota = request.getRequestURI();
            //String tpreq = request.getMethod();
            //List<Dcrcorsrq> perm = corsService.getIpsByRota(rota, clientIp);
            //Boolean haveAcess = false;
            
//            if(perm.isEmpty()) {
//            	response.setStatus(401);
//                response.getOutputStream()
//                        .println("Sem acesso a rota!");
//                return;
//            }else {
//            	for (Dcrcorsrq dcrcorsrq : perm) {
//					if(dcrcorsrq.getTpreq().toUpperCase().trim().equals(tpreq)) {
//						haveAcess = Boolean.TRUE;
//					}
//				}
//            }
//            
//            if(!haveAcess) {
//            	response.setStatus(401);
//                response.getOutputStream()
//                        .println("Sem acesso a rota!");
//                return;
//            }else {
            	UsernamePasswordAuthenticationToken authenticationToken = tokenService.getAuthenticationToken(token);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                chain.doFilter(request, response);
            //}
            
        } catch (TokenExpiredException e) {

            response.setStatus(401);
            response.getOutputStream()
                    .println("Seu Token expirou. Realize novo login no sistema!\nDetalhe: " + e.getMessage());

        } catch (JWTDecodeException e) {

            response.setStatus(401);
            response.getOutputStream()
                    .println("Token incorreto, verifique!");

        }

    }

}