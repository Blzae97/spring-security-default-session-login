package com.spring.security.domain.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RequestUserRegister {
    private String username;
    private String password;

    @Builder
    public RequestUserRegister(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * User DTO 생성
     *
     * @return User 도메인 DTO 객체
     */
    public UserRegisterItem toItem() {
        return UserRegisterItem.builder()
                .username(username)
                .password(password)
                .build();
    }
}
