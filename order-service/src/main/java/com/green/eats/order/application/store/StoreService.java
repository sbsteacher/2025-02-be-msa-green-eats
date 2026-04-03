package com.green.eats.order.application.store;

import com.green.eats.order.application.store.model.StoreMenuPutReq;
import com.green.eats.order.entity.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class StoreService {
    private final MenuRepository menuRepository;

    public List<Order> getAllMenus() {
        return menuRepository.findAll();
    }

    public Order getMenu(Long menuId) {
        return menuRepository.findById(menuId)
                .orElseThrow(() -> new RuntimeException("메뉴를 찾을 수 없습니다."));
    }

    @Transactional
    public void decreaseStock(StoreMenuPutReq req) {
        Order order = menuRepository.findById( req.menuId() )
                .orElseThrow(() -> new RuntimeException("메뉴를 찾을 수 없습니다."));
        order.removeStock( req.quantity() );
        // @Transactional에 의해 자동 dirty checking되어 업데이트됨
    }
}
