package com.xyb.web;

import com.xyb.domain.entity.UserEntity;
import com.xyb.response.RestInfo;
import com.xyb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 演示数据层操作，各种传参方式，参数校验
 * @Author lian
 * @Date 2018/3/20
 */
@RestController
@RequestMapping("/dao")
public class DAOController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public RestInfo getUserList() {
        List<UserEntity> list = userService.findAll();
        return new RestInfo<>(list);
    }

    @GetMapping("/get/{id}")
    public RestInfo getUser(@PathVariable("id") Long id) {
        UserEntity entity = userService.findById(id);
        return new RestInfo<>(entity);
    }


    @PostMapping("/save")
    public RestInfo save(@RequestBody @Validated UserEntity userEntity) {
        return new RestInfo<>(userService.save(userEntity));
    }

    @PostMapping("/delete/{id}")
    public RestInfo delete(@PathVariable Long id) {
        userService.deleteById(id);
        return new RestInfo();
    }

    @GetMapping("/page")
    public RestInfo page(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                         @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserEntity> list = userService.pageUser(pageable);
        return new RestInfo<>(list);
    }

    /**
     * 测试事务回滚
     * @param userEntity
     * @return
     */
    @PostMapping("/deleteWithTransaction")
    public RestInfo deleteWithTransaction(@RequestBody UserEntity userEntity){
        userService.deleteWithTransaction(userEntity);
        return new RestInfo<>();
    }
}
