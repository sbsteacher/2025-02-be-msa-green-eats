package com.green.eats.store.application.store;

import com.green.eats.store.application.store.model.StoreMenuPutReq;
import com.green.eats.store.entity.Menu;
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

    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    public Menu getMenu(Long menuId) {
        return menuRepository.findById(menuId)
                .orElseThrow(() -> new RuntimeException("메뉴를 찾을 수 없습니다."));
    }

    @Transactional
    public void decreaseStock(StoreMenuPutReq req) {
        Menu menu = menuRepository.findById( req.menuId() )
                .orElseThrow(() -> new RuntimeException("메뉴를 찾을 수 없습니다."));
        menu.removeStock( req.quantity() );
        // @Transactional에 의해 자동 dirty checking되어 업데이트됨
    }
}
