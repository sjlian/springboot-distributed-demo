package com.xyb.web;

import com.xyb.domain.entity.UserEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 演示user在内存中的操作
 * @Author lian
 * @Date 2018/3/20
 */
@RestController
@RequestMapping("/user1")
public class UserController {

    /**
     * 内存中保存，用于演示。ConcurrentHashMap是线程安全的map
     */
    private static Map<Long, UserEntity> users = new ConcurrentHashMap<>();

    @GetMapping("/list")
    public List<UserEntity> getUserList() {
        List<UserEntity> r = new ArrayList<>(users.values());
        return r;
    }

    @PostMapping(value="/add")
    public UserEntity addUser(@RequestBody @Valid UserEntity user) {
       //@RequestBody 会自动把前端传的json转换成user对象
        return user;
    }


    @GetMapping(value="/get/{id}")
    public UserEntity getUser(@PathVariable Long id) {
        // 处理"/users/{id}"的GET请求，用来获取url中id值的User信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        return users.get(id);
    }

    @PostMapping(value="/update/{id}")
    public String updateUser(@PathVariable Long id,@RequestBody UserEntity user) {
        UserEntity u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        return "success";
    }
}
