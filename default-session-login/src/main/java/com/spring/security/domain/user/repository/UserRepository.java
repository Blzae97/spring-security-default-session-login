package com.spring.security.domain.user.repository;

import com.spring.security.domain.user.dto.UserRegisterItem;

public interface UserRepository {
    void userRegister(UserRegisterItem item); // 유저 엔티티 저장
}
