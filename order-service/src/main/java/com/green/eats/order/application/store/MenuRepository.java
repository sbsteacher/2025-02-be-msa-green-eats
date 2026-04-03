package com.green.eats.order.application.store;

import com.green.eats.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Order, Long> {
}
