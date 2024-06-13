package com.spring.security.domain.user.repository;

import com.spring.security.domain.user.dto.UserRegisterItem;

public interface UserRepository {
    <T> T userRegister(UserRegisterItem item, Class<T> tClass); // 유저 엔티티 저장
}
