package com.xyb.web;

import com.xyb.domain.entity.OrderEntity;
import com.xyb.domain.entity.UserEntity;
import com.xyb.response.RestInfo;
import com.xyb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public RestInfo listUser() {
        return new RestInfo<>(orderService.list());
    }

    @GetMapping("/get/{orderNo}")
    public RestInfo getUser(@PathVariable("orderNo") Long orderNo) {
        OrderEntity entity = orderService.getOrder(orderNo);
        return new RestInfo<>(entity);
    }

    @PostMapping("/save")
    public RestInfo save(@RequestBody @Validated OrderEntity entity) {
        return new RestInfo<>(orderService.saveOrder(entity));
    }
}
