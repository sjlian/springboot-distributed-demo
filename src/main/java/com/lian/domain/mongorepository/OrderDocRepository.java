package com.lian.domain.mongorepository;

import com.lian.domain.entity.OrderDoc;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author lian
 * @Date 2019-07-26
 */
@Repository
public interface OrderDocRepository extends MongoRepository<OrderDoc, String> {
}
