package com.spring.security.infrastructure.user.repository;

import com.spring.security.domain.user.entity.UserAuthority;
import com.spring.security.domain.user.enums.Authority;
import com.spring.security.domain.user.repository.UserAuthorityRepository;
import com.spring.security.infrastructure.user.repository.inf.UserAuthorityJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class DefaultUserAuthorityRepository implements UserAuthorityRepository {

    private final UserAuthorityJpaRepository userAuthorityJpaRepository;

    public DefaultUserAuthorityRepository(UserAuthorityJpaRepository userAuthorityJpaRepository) {
        this.userAuthorityJpaRepository = userAuthorityJpaRepository;
    }

    /**
     * <p>회원 권한 저장</p>
     *
     * @param userId    회원 고유 아이디
     * @param authority 권한 타입
     */
    @Transactional
    @Override
    public void grantAuthority(Long userId, Authority authority) {
        userAuthorityJpaRepository.save(UserAuthority.builder()
                .userId(userId)
                .authority(authority)
                .build()
        );
    }

    /**
     * <p>회원 권한 조회</p>
     *
     * @param userId 회원 고유 아이디
     * @param tClass 반환 타입
     * @return 회원 권한 리스트
     */
    @Transactional(readOnly = true)
    @Override
    public <T> List<T> getUserAuthorityList(Long userId, Class<T> tClass) {
        return userAuthorityJpaRepository.findByUserId(userId, tClass);
    }


}
