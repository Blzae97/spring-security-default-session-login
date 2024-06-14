package com.spring.security.infrastructure.user.repository.inf;

import com.spring.security.domain.user.entity.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserAuthorityJpaRepository extends JpaRepository<UserAuthority, Long> {

    @Query(value =
            "SELECT ua " +
            "FROM UserAuthority ua " +
            "WHERE ua.userId = :userId"
    )
    <T> List<T> findByUserId(Long userId, Class<T> tClass);
}
