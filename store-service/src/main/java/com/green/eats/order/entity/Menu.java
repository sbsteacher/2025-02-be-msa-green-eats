package com.green.eats.order.entity;

import io.hypersistence.utils.hibernate.id.Tsid;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import tools.jackson.databind.annotation.JsonSerialize;
import tools.jackson.databind.ser.std.ToStringSerializer;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Menu {
    @Id
    @Tsid
    private Long id;

    private String name;
    private Integer price;
    private Integer stockQuantity;

    public void removeStock(int quantity) {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) {
            throw new RuntimeException("상품 '" + name + "'의 재고가 부족합니다. (현재: " + stockQuantity + ")");
        }
        this.stockQuantity = restStock;
    }
}
