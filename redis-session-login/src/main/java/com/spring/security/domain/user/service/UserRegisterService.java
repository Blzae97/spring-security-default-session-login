package com.spring.security.domain.user.service;

import com.spring.security.domain.user.dto.UserRegisterItem;

public interface UserRegisterService {
    void register(UserRegisterItem item); // 유저 정보 저장, 비밀번호 암호화
}
