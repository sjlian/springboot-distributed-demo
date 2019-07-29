package com.lian.web;

import com.lian.domain.entity.OrderEntity;
import com.lian.web.response.RestInfo;
import com.lian.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public RestInfo listOrder() {
        return new RestInfo<>(orderService.list());
    }

    @GetMapping("/get/{orderNo}")
    public RestInfo getOrder(@PathVariable("orderNo") Long orderNo) {
        OrderEntity entity = orderService.getOrder(orderNo);
        return new RestInfo<>(entity);
    }

    @PostMapping("/save")
    public RestInfo save(@RequestBody @Validated OrderEntity entity) {
        return new RestInfo<>(orderService.saveOrder(entity));
    }
}
