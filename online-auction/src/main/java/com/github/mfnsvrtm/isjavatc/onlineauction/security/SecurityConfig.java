package com.github.mfnsvrtm.isjavatc.onlineauction.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // Disable CSRF protection for ease for testing
        httpSecurity.csrf().disable();

        httpSecurity.authorizeHttpRequests(customizer -> {
            customizer.requestMatchers(HttpMethod.POST, "/api/auth/login").permitAll();
            customizer.requestMatchers(HttpMethod.POST, "/api/users").permitAll();
            customizer.requestMatchers(HttpMethod.GET, "/api/lots").permitAll();
            customizer.anyRequest().authenticated();
        });

        httpSecurity.formLogin(customizer -> {
            customizer.loginProcessingUrl("/api/auth/login");
            customizer.successHandler(new LoginSuccessHandler());
            customizer.failureHandler(new LoginFailureHandler());
        });

        httpSecurity.logout(customizer -> {
            customizer.logoutRequestMatcher(AntPathRequestMatcher.antMatcher(HttpMethod.POST, "/api/auth/logout"));
            customizer.logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK));
        });

        httpSecurity.exceptionHandling(customizer -> {
            customizer.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
        });

        return httpSecurity.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
