package com.lian.domain.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_order")
@Setter
@Getter
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
}
