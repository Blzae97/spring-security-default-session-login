package com.spring.security.infrastructure.user.repository.inf;

import com.spring.security.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserJpaRepository extends JpaRepository<User, Long> {

    @Query(value =
            "SELECT u " +
            "FROM User u " +
            "WHERE u.username = :username")
    <T> T findByUserName(String username, Class<T> tClass);
}
