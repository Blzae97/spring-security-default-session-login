package com.spring.security.infrastructure.user.repository;

import com.spring.security.domain.user.dto.UserRegisterItem;
import com.spring.security.domain.user.entity.User;
import com.spring.security.domain.user.repository.UserRepository;
import com.spring.security.infrastructure.user.repository.inf.UserJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public class DefaultUserRepository implements UserRepository {
    private final UserJpaRepository userJpaRepository;

    public DefaultUserRepository(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    /**
     * User 엔티티 저장 후 인스턴스 반환
     *
     * @param item User 도메인 DTO
     */
    @Transactional
    @Override
    public <T> T userRegister(UserRegisterItem item, Class<T> tClass) {
        User userEntity = item.toEntity();
        User savedUserEntity = userJpaRepository.save(userEntity);

        return instantiateWithUser(savedUserEntity, tClass);
    }

    /**
     * username으로 회원 정보 조회
     *
     * @param username 회원 아이디
     * @param tClass 반환 타입
     * @return 회원정보
     */
    @Transactional(readOnly = true)
    @Override
    public <T> Optional<T> getUser(String username, Class<T> tClass) {
        return Optional.ofNullable(userJpaRepository.findByUserName(username, tClass));
    }


    /**
     * User 엔티티를 T 클래스로 인스턴스화 하여 반횐
     * User 관련 DTO가 들어와도 변환할 수 있게 제너릭을 사용
     *
     * @param u      회원 엔티티
     * @param tClass 변환 하고 싶은 클래스
     * @return tClass 구현체
     */
    private <T> T instantiateWithUser(User u, Class<T> tClass) {
        try {
            Constructor<T> constructor = tClass.getDeclaredConstructor(User.class);
            return constructor.newInstance(u);

        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
