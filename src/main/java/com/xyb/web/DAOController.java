package com.xyb.web;

import com.xyb.domain.entity.UserEntity;
import com.xyb.exception.RestInfo;
import com.xyb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
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

    @PostMapping("/delete/{id}")
    public RestInfo delete(@PathVariable Long id) {
        userService.deleteById(id);
        return new RestInfo();
    }
}
