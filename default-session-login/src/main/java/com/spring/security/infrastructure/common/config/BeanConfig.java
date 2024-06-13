package com.spring.security.infrastructure.common.config;

import com.spring.security.application.user.service.UserService;
import com.spring.security.domain.user.repository.UserAuthorityRepository;
import com.spring.security.domain.user.repository.UserRepository;
import com.spring.security.domain.user.service.UserRegisterService;
import com.spring.security.infrastructure.user.repository.DefaultUserRepository;
import com.spring.security.infrastructure.user.repository.inf.UserJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BeanConfig {

    /**
     * SCrypt 암호화 방식 적용
     *
     * @return PasswordEncoder 객체
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        Map<String, PasswordEncoder> encoderMap = new HashMap<>();
        String encodingId = "scrypt@SpringSecurity_v5_8";
        encoderMap.put(encodingId, SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8()); // SCrypt 암호화 방식 선택
        return new DelegatingPasswordEncoder(encodingId, encoderMap);
    }

    /**
     * UserRepository 빈 등록
     *
     * @param userJpaRepository User 도메인의 JPA Repository
     * @return DefaultUserRepository 객체
     */
    @Bean
    public UserRepository userRepository(UserJpaRepository userJpaRepository) {
        return new DefaultUserRepository(userJpaRepository);
    }

    /**
     * UserRegisterService 빈 등록
     *
     * @param userRepository  User 도메인의 기본 레포지터리 객체
     * @param passwordEncoder SCryptPasswordEncoder 객체
     * @return UserService 객체
     */
    @Bean
    public UserRegisterService userRegisterService(UserRepository userRepository,
                                                   UserAuthorityRepository userAuthorityRepository,
                                                   PasswordEncoder passwordEncoder) {
        return new UserService(userRepository, userAuthorityRepository, passwordEncoder);
    }
}
