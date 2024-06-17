package com.spring.security.domain.user.repository;

import com.spring.security.domain.user.enums.Authority;

import java.util.List;

public interface UserAuthorityRepository {

    /**
     * <p>권한 부여</p>
     *
     * @param userId 사용자 고유 아이디
     * @param authority 권한
     */
    void grantAuthority(Long userId, Authority authority);

    /**
     * <p>회원의 권한 리스트 조회</p>
     *
     * @param userId 사용자 고유 아이디
     * @param tClass 반환 타입
     * @return T 타입의 권한 리스트
     */
    <T> List<T> getUserAuthorityList(Long userId, Class<T> tClass); // 사용자 권한 리스트 조회
}
