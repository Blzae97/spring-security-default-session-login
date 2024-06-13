package com.spring.security.infrastructure.user.repository.inf;

import com.spring.security.domain.user.entity.UserAuthority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAuthorityJpaRepository extends JpaRepository<UserAuthority, Long> {
}
