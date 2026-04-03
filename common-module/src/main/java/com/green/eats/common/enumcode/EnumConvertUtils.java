package com.green.eats.common.enumcode;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.EnumSet;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnumConvertUtils {
    //특정 Enum 클래스에서 code값과 일치하는 Enum 상수를 반환
    public static <E extends Enum<E> & EnumMapperType> E ofCode(Class<E> enumClass, String code) {
        if (code == null || code.isBlank()) {
            return null;
        }

        return EnumSet.allOf(enumClass).stream()
                .filter(item -> item.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Unknown code [%s] for enum [%s]", code, enumClass.getSimpleName())));
    }

    public static <E extends Enum<E> & EnumMapperType> String toCode(E enumItem) {
        if (enumItem == null) { return null; }
        return enumItem.getCode();
    }
}
