package com.dcr.api.configs.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.dcr.api.service.AuthenticationService;



@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {


    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private FilterToken filter;

    @Bean
    InMemoryUserDetailsManager users() {
        return new InMemoryUserDetailsManager(
                User.withUsername("dvega")
                        .password("{noop}password")
                        .roles("USER")
                        .build());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(authenticationService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        http
                .cors(Customizer.withDefaults())
                .csrf((csrf) -> csrf
                        .disable())
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/api/user/**").permitAll()
                        .requestMatchers("/api/roles/**").permitAll()
                        .requestMatchers("/api/processamento/**").permitAll()
                        .requestMatchers("/api/config/**").permitAll()
                        .requestMatchers("/api/regras/**").permitAll()
                        .requestMatchers("/api/pendencia/**").permitAll()
                        .requestMatchers("/api/produto/**").permitAll()
                        .requestMatchers("/api/astec/**").permitAll()
                        .requestMatchers("/api/taxa/**").permitAll()
                        .requestMatchers("/api/cor/**").permitAll()
                        .requestMatchers("/api/incoterm/**").permitAll()
                        .requestMatchers("/api/matriz/**").permitAll()
                        .requestMatchers("/api/operacoes/**").permitAll()
                        .requestMatchers("/api/idioma/**").permitAll()
                        .requestMatchers("/api/module/**").permitAll()
                        .requestMatchers("/api/processo/dcr/**").permitAll()
                        .requestMatchers("/api/registro/**").permitAll()
                        .requestMatchers("/api/descricaoModulo/**").permitAll()
                        .requestMatchers("/api/descricaoOperacao/**").permitAll()
                        .requestMatchers("/api/securityuser/add").permitAll()
                        .requestMatchers("/api/securityuser/changepassword").permitAll()
                        .requestMatchers("/api/securityuser/getall").permitAll()
                        .requestMatchers("/api/layout").permitAll()
                        .requestMatchers("/api/coligado/**").permitAll()
                        .requestMatchers("/api/sistema/**").permitAll()
                        .requestMatchers("/api/txt/**").permitAll()
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/health-check/**").permitAll()
                        .requestMatchers("/api/prodfat/**").permitAll()
                        .requestMatchers("/api/prodmod/**").permitAll()
                        .requestMatchers("/api/protocolo/**").permitAll()
                        .requestMatchers("/api/vigencia/**").permitAll()
                        .requestMatchers("/api/test/**").permitAll()
                        .requestMatchers("/api/cors/**").permitAll()
                        .requestMatchers("/api/external/**").permitAll()
                        .requestMatchers("/api/securityuser/**").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**").permitAll()
                        .requestMatchers("/**.html").permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/wellcome/**").permitAll())
                

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

        ;

        http.authenticationProvider(authenticationProvider());
        return http.build();
    }

}