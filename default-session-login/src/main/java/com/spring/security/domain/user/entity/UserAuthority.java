package com.spring.security.domain.user.entity;

import com.spring.security.domain.user.converter.AuthorityConverter;
import com.spring.security.domain.user.enums.Authority;
import com.spring.security.infrastructure.common.entity.DateTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@NoArgsConstructor
@Getter
@Entity
public class UserAuthority extends DateTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int(11)")
    @Comment(value = "고유 아이디")
    private Long id;

    @Column(name = "user_id", columnDefinition = "int(11)")
    @Comment(value = "회원 고유 아이디")
    private Long userId;

    @Convert(converter = AuthorityConverter.class)
    @Column(name = "authority", columnDefinition = "int(3)")
    @Comment(value = "회원 권한")
    private Authority authority;

    @Builder
    public UserAuthority(Long id, Long userId, Authority authority) {
        this.id = id;
        this.userId = userId;
        this.authority = authority;
    }
}
