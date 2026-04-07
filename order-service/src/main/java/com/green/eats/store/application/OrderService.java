package com.green.eats.store.application;

import com.green.eats.store.application.model.OrderPostReq;
import com.green.eats.store.application.model.OrderStatus;
import com.green.eats.store.entity.Order;
import com.green.eats.store.entity.OrderItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;

    @Transactional
    public Long createOrder(OrderPostReq dto) {
        Order order = new Order();
        order.setUserId(dto.getUserId());
        order.setStatus(OrderStatus.ORDERED);

        dto.getItems().forEach(itemDto -> {
            OrderItem item = OrderItem.builder()
                    .menuId(itemDto.getMenuId())
                    .quantity(itemDto.getQuantity())
                    .price(itemDto.getPrice())
                    .build();
            order.addOrderItem(item);
        });

        return orderRepository.save(order).getId();
    }
}
