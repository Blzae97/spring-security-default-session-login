package com.spring.security.application.user.service;

import com.spring.security.domain.user.dto.UserItem;
import com.spring.security.domain.user.dto.UserRegisterItem;
import com.spring.security.domain.user.enums.Authority;
import com.spring.security.domain.user.repository.UserAuthorityRepository;
import com.spring.security.domain.user.repository.UserRepository;
import com.spring.security.domain.user.service.UserRegisterService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

public class UserService implements UserRegisterService {

    private final UserRepository userRepository;
    private final UserAuthorityRepository userAuthorityRepository;
    private final PasswordEncoder passwordEncoder;

    private final Authority DEFAULT_AUTHORITY = Authority.ROLE_READ;

    public UserService(UserRepository userRepository,
                       UserAuthorityRepository userAuthorityRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userAuthorityRepository = userAuthorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 유저 정보 저장, 비밀번호 암호화
     *
     * @param item 회원정보 객체
     */
    @Transactional
    @Override
    public void register(UserRegisterItem item) {
        String encodePassword = encodePassword(item.getPassword()); // 비밀번호 암호화 호출
        item.encryptPassword(encodePassword); // 암호화 된 비밀번호로 업데이트

        UserItem userItem = userRepository.userRegister(item, UserItem.class);// 회원정보 저장 메소드 호출
        userAuthorityRepository.grantAuthority(userItem.getId(), DEFAULT_AUTHORITY); // 기본 권한으로 읽기 권한만 부여
    }

    /**
     * SCryptPasswordEncoder를 이용한 비밀번호 암호화 메서드
     *
     * @param password 비밀번호
     * @return 암호화 된 문자열
     */
    private String encodePassword(String password){
        return passwordEncoder.encode(password);
    }
}
