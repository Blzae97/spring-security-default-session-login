package com.spring.security.domain.user.entity;

import com.spring.security.infrastructure.common.entity.DateTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@NoArgsConstructor
@Getter
@Entity
public class User extends DateTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "int(11)")
    @Comment(value = "고유 아이디")
    private Long id;

    @Column(name = "username", columnDefinition = "varchar(100)")
    @Comment(value = "아이디")
    private String username;

    @Column(name = "password", columnDefinition = "varchar(255)")
    @Comment(value = "비밀번호")
    private String password;

    @Builder
    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
