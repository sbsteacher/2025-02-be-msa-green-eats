package com.green.eats.store.application.model;

import com.green.eats.common.enumcode.AbstractEnumCodeConverter;
import com.green.eats.common.enumcode.EnumMapperType;
import jakarta.persistence.Converter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus implements EnumMapperType {
    ORDERED("01", "주문완료"),
    PAYMENT_COMPLETED("02", "결제완료"),
    PREPARING("03", "상품준비중"),
    SHIPPING("04", "배송중"),
    COMPLETED("05", "배송완료"),
    CANCELLED("06", "주문취소");

    private final String code;
    private final String value;

    @Converter(autoApply = true)
    public static class CodeConverter extends AbstractEnumCodeConverter<OrderStatus> {
        public CodeConverter() { super(OrderStatus.class, false); }
    }
}
