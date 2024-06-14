package com.spring.security.infrastructure.security.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;
    private final AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> detailsSource;

    public SecurityConfig(@Qualifier(value = "formUserAuthenticationProvider") AuthenticationProvider authenticationProvider,
                          AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> detailsSource) {
        this.authenticationProvider = authenticationProvider;
        this.detailsSource = detailsSource;
    }


    /**
     * 시큐리티 필터 체인 설정 메서드
     *
     * @param http HttpSecurity
     * @return SecurityFilterChain
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**").permitAll() // css 파일 접근 허용
                        .requestMatchers("/login").permitAll() // login 경로 접근 허용
                        .requestMatchers("/logout").permitAll() // logout 경로 접근 허용
                        .requestMatchers("/register").permitAll() // register 경로 접근 허용
                        .requestMatchers("/").permitAll() // 루트 경로는 허용
                        .anyRequest().authenticated() // 그 외 경로는 인증 받아야 함
                );

        http
                .formLogin(form -> form
                        .loginPage("/login") // 커스텀 로그인 페이지 지정
                        .authenticationDetailsSource(detailsSource)
                );

        http
                .authenticationProvider(authenticationProvider);

        return http.build();
    }
}
