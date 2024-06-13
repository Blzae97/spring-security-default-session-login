package com.spring.security.domain.user.repository;

import com.spring.security.domain.user.enums.Authority;

public interface UserAuthorityRepository {
    void grantAuthority(Long userId, Authority authority); // 권한 부여
}
