package com.spring.security.infrastructure.user.repository;

import com.spring.security.domain.user.entity.UserAuthority;
import com.spring.security.domain.user.enums.Authority;
import com.spring.security.domain.user.repository.UserAuthorityRepository;
import com.spring.security.infrastructure.user.repository.inf.UserAuthorityJpaRepository;
import org.springframework.transaction.annotation.Transactional;

public class DefaultUserAuthorityRepository implements UserAuthorityRepository {

    private final UserAuthorityJpaRepository jpaRepository;

    public DefaultUserAuthorityRepository(UserAuthorityJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    /**
     * 회원 권한 저장
     * @param userId 회원 고유 아이디
     * @param authority 권한 타입
     */
    @Transactional
    @Override
    public void grantAuthority(Long userId, Authority authority) {
        jpaRepository.save(UserAuthority.builder()
                .userId(userId)
                .authority(authority)
                .build()
        );
    }
}
