package com.green.eats.order.application.store;

import com.green.eats.order.Dummy;
import com.green.eats.order.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MenuDummyTest extends Dummy {
    @Autowired MenuRepository menuRepository;

    @Test
    void insDummyList() {
        final int size = 20;

        for(int i=0; i<size; i++) {
            Order order = new Order();
            order.setName(koFaker.food().dish());
            order.setPrice(koFaker.random().nextInt(10000, 50000));
            order.setStockQuantity(koFaker.random().nextInt(5, 40));
            menuRepository.save(order);
        }
        menuRepository.flush();
    }
}
