package com.green.eats.store.application.store;

import com.green.eats.common.model.ResultResponse;
import com.green.eats.common.model.UserContext;
import com.green.eats.common.model.UserDto;
import com.green.eats.store.application.store.model.StoreMenuPutReq;
import com.green.eats.store.entity.Menu;
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
    @GetMapping("/menu")
    public ResultResponse<?> getAllMenus() {
        UserDto userDto = UserContext.get();
        log.info("userDto: {}", userDto);

        List<Menu> menus = storeService.getAllMenus();
        return ResultResponse.builder()
                .resultMessage(String.format("%d rows", menus.size()))
                .resultData(menus)
                .build();
    }

    // 단일 메뉴 조회 (Order 서비스 호출용)
    @GetMapping("/menu/{menuId}")
    public Menu getMenu(@PathVariable Long menuId) {
        return storeService.getMenu(menuId);
    }

    // 재고 차감 (Order 서비스 호출용)
    @PutMapping("/menu/{menuId}/decrease-stock")
    public int decreaseStock(@PathVariable Long menuId, @RequestParam int quantity) {
        StoreMenuPutReq req = new StoreMenuPutReq(menuId, quantity);
        storeService.decreaseStock(req);
        return 1;
    }
}
