package com.green.eats.order.application.store;

import com.green.eats.order.application.store.model.StoreMenuPutReq;
import com.green.eats.order.entity.Menu;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    // 모든 메뉴 조회 (Vue 화면용)
    @GetMapping("/menus")
    public List<Menu> getAllMenus() {
        return storeService.getAllMenus();
    }

    // 단일 메뉴 조회 (Order 서비스 호출용)
    @GetMapping("/menus/{menuId}")
    public Menu getMenu(@PathVariable Long menuId) {
        return storeService.getMenu(menuId);
    }

    // 재고 차감 (Order 서비스 호출용)
    @PutMapping("/menus/{menuId}/decrease-stock")
    public int decreaseStock(@PathVariable Long menuId, @RequestParam int quantity) {
        StoreMenuPutReq req = new StoreMenuPutReq(menuId, quantity);
        storeService.decreaseStock(req);
        return 1;
    }
}
