package com.lian.service;

import com.lian.domain.entity.OrderEntity;

import java.util.List;

public interface OrderService {
    OrderEntity saveOrder(OrderEntity orderEntity);

    OrderEntity getOrder(Long orderNo);

    List<OrderEntity> list();
}
