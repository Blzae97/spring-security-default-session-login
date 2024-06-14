package com.spring.security.domain.user.repository;

import com.spring.security.domain.user.dto.UserRegisterItem;

import java.util.Optional;

public interface UserRepository {

    /**
     * <p>회원 엔티티 저장</p>
     *
     * @param item   회원 정보
     * @param tClass 반환 타입
     * @return T 타입의 회원 정보
     */
    <T> T userRegister(UserRegisterItem item, Class<T> tClass); // 유저 엔티티 저장

    /**
     * <p>회원은 username으로 회원 정보 조회</p>
     *
     * @param username 회원 이메일
     * @param tClass   반환 타입
     * @return T 타입의 회원 정보
     */
    <T> Optional<T> getUser(String username, Class<T> tClass); // username으로 회원조회
}
