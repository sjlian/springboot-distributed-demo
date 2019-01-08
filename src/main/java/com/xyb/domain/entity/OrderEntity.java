package com.xyb.domain.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_order")
public class OrderEntity {
    /**
     * 根据订单生成器生成
     */
    @Id
    @Column(name = "order_no")
    @ApiModelProperty("订单号")
    private Long orderNo;

    @Column(name = "user_id")
    @ApiModelProperty("用户id")
    private Long userId;

    //TODO other params


    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
