package com.green.eats.order.application;

import com.green.eats.common.model.UserContext;
import com.green.eats.common.model.UserDto;
import com.green.eats.order.application.model.OrderPostReq;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Long> placeOrder(@Valid @RequestBody OrderPostReq req) {
        UserDto userDto = UserContext.get();
        log.info("userDto: {}", userDto);
        req.setUserId(userDto.id());
        Long orderId = orderService.createOrder(req);
        return ResponseEntity.ok(orderId);
    }
}
