package com.lian.domain.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

/**
 * @Author lian
 * @Date 2019-07-26
 */
@Document(collection = "order")
@Setter
@Getter
public class OrderDoc {
    @Id
    private String id;

    @Indexed
    private Long orderNo;

    private Long userId;
}
