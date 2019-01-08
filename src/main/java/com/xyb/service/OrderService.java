package com.xyb.service;

import com.xyb.domain.entity.OrderEntity;

import java.util.List;

public interface OrderService {
    OrderEntity saveOrder(OrderEntity orderEntity);

    OrderEntity getOrder(Long orderSn);

    List<OrderEntity> list();
}
