package com.spring.security.application.user.provider;

import com.spring.security.application.user.details.FormAuthenticationDetails;
import com.spring.security.domain.user.dto.DefaultUserItem;
import com.spring.security.domain.user.dto.UserContext;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class FormUserAuthenticationProvider implements AuthenticationProvider {
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public FormUserAuthenticationProvider(@Qualifier(value = "formLoginUserDetailsService") UserDetailsService userDetailsService,
                                          PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * <p>인증처리</p>
     * <p>이 메서드에서 대부분의 보안이나 인증처리 로직이 들어가야 함</p>
     *
     * @param authentication 인증 객체
     * @return UsernamePasswordAuthenticationToken 인증 객체
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();

        UserContext userContext = (UserContext) userDetailsService.loadUserByUsername(username);
        if (!passwordEncoder.matches(password, userContext.getPassword())) { // 비밀번호 검증
            throw new BadCredentialsException("회원정보가 일치하지 않습니다.");
        }

        FormAuthenticationDetails details = (FormAuthenticationDetails) authentication.getDetails(); // 해당 부분에서 추가적인 인증 처리가 가능하다.
        System.out.println("secretKey: " + details.getSecretKey());

        DefaultUserItem userItem = userContext.getUserItem();
        userItem.hidePassword(); // 비밀번호 null 처리

        return new UsernamePasswordAuthenticationToken(userItem, null, userContext.getAuthorities());
    }

    /**
     * <p>Authentication 객체를 이 AuthenticationProvider가 처리할 수 있는지 여부를 결정합니다.</p>
     *
     * @param authentication 인증 객체
     * @return true: 처리 가능, false: 처리 불가능
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
}
