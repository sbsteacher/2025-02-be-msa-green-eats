package com.green.eats.store.entity;

import com.green.eats.store.application.model.OrderStatus;
import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="orders")
public class Order {
    @Id @Tsid
    private Long id;

    private Long userId;

    @Column(nullable = false)
    private long totalPrice;

    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    // 편의 메서드: 주문 항목 추가 시 양방향 연결을 자동으로 처리
    public void addOrderItem(OrderItem item) {
        items.add(item);
        item.setOrder(this);
        calculateTotalPrice();
    }

    private void calculateTotalPrice() {
        this.totalPrice = items.stream()
                .mapToLong(OrderItem::getTotalItemPrice)
                .sum();
    }
}
