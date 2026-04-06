package com.green.eats.common.model;

import com.green.eats.common.enumcode.EnumMapperType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole implements EnumMapperType {
    USER("01", "일반유저"),
    ADMIN("02", "관리자")
    ;
    private final String code;
    private final String value;
}
