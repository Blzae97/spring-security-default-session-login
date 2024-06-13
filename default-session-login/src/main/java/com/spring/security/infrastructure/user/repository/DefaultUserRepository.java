package com.spring.security.infrastructure.user.repository;

import com.spring.security.domain.user.dto.UserRegisterItem;
import com.spring.security.domain.user.entity.User;
import com.spring.security.domain.user.repository.UserRepository;
import com.spring.security.infrastructure.user.repository.inf.UserJpaRepository;
import org.springframework.transaction.annotation.Transactional;

public class DefaultUserRepository implements UserRepository {
    private final UserJpaRepository jpaRepository;

    public DefaultUserRepository(UserJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    /**
     * User 엔티티 저장 메서드
     *
     * @param item User 도메인 DTO
     */
    @Transactional
    @Override
    public void userRegister(UserRegisterItem item) {
        User userEntity = item.toEntity();
        jpaRepository.save(userEntity);
    }
}
