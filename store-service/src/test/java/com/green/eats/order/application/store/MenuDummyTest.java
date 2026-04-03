package com.green.eats.order.application.store;

import com.green.eats.order.Dummy;
import com.green.eats.order.entity.Menu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MenuDummyTest extends Dummy {
    @Autowired MenuRepository menuRepository;

    @Test
    void insDummyList() {
        final int size = 20;

        for(int i=0; i<size; i++) {
            Menu menu = new Menu();
            menu.setName(koFaker.food().dish());
            menu.setPrice(koFaker.random().nextInt(10000, 50000));
            menu.setStockQuantity(koFaker.random().nextInt(5, 40));
            menuRepository.save(menu);
        }
        menuRepository.flush();
    }
}
