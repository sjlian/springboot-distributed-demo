package com.lian.web;

import com.lian.domain.entity.OrderDoc;
import com.lian.domain.mongorepository.OrderDocRepository;
import com.lian.common.response.RestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @Author lian
 * @Date 2019-07-26
 */
@RestController
@RequestMapping("/mongo")
public class MongoController {
    @Autowired
    private OrderDocRepository orderDocRepository;

    @GetMapping("/list")
    public RestInfo getUserList() {
        List<OrderDoc> list = orderDocRepository.findAll();
        return new RestInfo<>(list);
    }

    @GetMapping("/get/{id}")
    public RestInfo getUser(@PathVariable("id") String id) {
        Optional<OrderDoc> entityOption = orderDocRepository.findById(id);
        return new RestInfo<>(entityOption);
    }

    @PostMapping("/save")
    public RestInfo save(@RequestBody @Validated OrderDoc orderDoc) {
        return new RestInfo<>(orderDocRepository.save(orderDoc));
    }

    @PostMapping("/del/{id}")
    public RestInfo del(@PathVariable("id") String id) {
        orderDocRepository.deleteById(id);
        return new RestInfo<>();
    }
}
