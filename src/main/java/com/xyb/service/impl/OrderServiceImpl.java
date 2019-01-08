package com.xyb.service.impl;

import com.xyb.domain.entity.OrderEntity;
import com.xyb.domain.repository.OrderRepository;
import com.xyb.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public OrderEntity saveOrder(OrderEntity orderEntity) {
        return orderRepository.save(orderEntity);
    }

    @Override
    public OrderEntity getOrder(Long orderNo) {
        Optional<OrderEntity> optional =  orderRepository.findById(orderNo);
        return optional.orElseGet(OrderEntity::new);
    }

    @Override
    public List<OrderEntity> list() {
        return orderRepository.findAll();
    }
}
