package com.spring.security.domain.user.converter;

import com.spring.security.domain.user.enums.Authority;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class AuthorityConverter implements AttributeConverter<Authority, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Authority attribute) {
        if(attribute.equals(Authority.ROLE_ADMIN)){
            return 0;
        }

        if(attribute.equals(Authority.ROLE_READ)){
            return 1;
        }

        if(attribute.equals(Authority.ROLE_WRITE)){
            return 2;
        }

        if(attribute.equals(Authority.ROLE_ANONYMOUS)){
            return 3;
        }

        throw new IllegalArgumentException("Authority 변환에 실패했습니다.");
    }

    @Override
    public Authority convertToEntityAttribute(Integer dbData) {
        if(dbData == 0){
            return Authority.ROLE_ADMIN;
        }

        if(dbData == 1){
            return Authority.ROLE_READ;
        }

        if(dbData == 2){
            return Authority.ROLE_WRITE;
        }

        if(dbData == 3){
            return Authority.ROLE_ANONYMOUS;
        }

        throw new IllegalArgumentException("Authority 변환에 실패했습니다.");
    }
}
