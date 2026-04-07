package com.green.eats.store.application.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class OrderPostReq {
    private Long userId;

    @NotEmpty(message = "주문 항목이 최소 하나 이상 있어야 합니다.")
    @Valid // 내부에 있는 OrderItemRequest의 검증 로직도 활성화
    private List<OrderItemPostReq> items;
}
