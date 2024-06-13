package com.spring.security.infrastructure.repository.user.inf;

import com.spring.security.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {
}
