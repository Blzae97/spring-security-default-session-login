package com.spring.security.application.user.service;

import com.spring.security.domain.user.dto.DefaultUserItem;
import com.spring.security.domain.user.dto.UserAuthorityItem;
import com.spring.security.domain.user.dto.UserContext;
import com.spring.security.domain.user.dto.UserItem;
import com.spring.security.domain.user.repository.UserAuthorityRepository;
import com.spring.security.domain.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormLoginUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserAuthorityRepository userAuthorityRepository;

    public FormLoginUserDetailsService(UserRepository userRepository,
                                       UserAuthorityRepository userAuthorityRepository) {
        this.userRepository = userRepository;
        this.userAuthorityRepository = userAuthorityRepository;
    }

    /**
     * <p>회원 아이디, 비밀번호 검증 후 정보 반환</p>
     *
     * @param username 회원 아이디
     * @return 회원 객체
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserItem userItem = userRepository.getUser(username, UserItem.class)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("%s으로 조회 된 회원이 없습니다.", username)));

        DefaultUserItem defaultUserItem = userItem.toDefaultUserItem();
        List<UserAuthorityItem> userAuthorityList = userAuthorityRepository.getUserAuthorityList(
                userItem.getId(),
                UserAuthorityItem.class
        );

       return new UserContext(defaultUserItem, userAuthorityList);
    }
}
