package com.green.eats.order.entity;

import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderItem {
    @Id
    @Tsid
    private Long id;

    @Column(nullable = false)
    private Long menuId;

    // 주문 수량
    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private int price;

    // 편의를 위한 생성자
    public OrderItem(Long menuId, int quantity, int price) {
        this.menuId = menuId;
        this.quantity = quantity;
        this.price = price;
    }

    //항목별 총 금액 계산 (단가 * 수량)
    public long getTotalItemPrice() {
        return (long) this.price * this.quantity;
    }
}
