package com.spring.security.infrastructure.security.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final AuthenticationProvider authenticationProvider;

    public SecurityConfig(@Qualifier(value = "formUserAuthenticationProvider") AuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
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
                        .requestMatchers("/register").permitAll() // register 경로 접근 허용
                        .requestMatchers("/").permitAll() // 루트 경로는 허용
                        .anyRequest().authenticated() // 그 외 경로는 인증 받아야 함
                );

        http
                .formLogin(form -> form
                        .loginPage("/login") // 커스텀 로그인 페이지 지정
                );

        http
                .authenticationProvider(authenticationProvider);

        return http.build();
    }
}
