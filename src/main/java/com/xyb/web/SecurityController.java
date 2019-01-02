package com.xyb.web;

import com.xyb.domain.entity.UserEntity;
import com.xyb.response.RestInfo;
import com.xyb.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 演示shiro权限注解控制
 * @Author lian
 * @Date 2018/3/20
 */
@RestController
@RequestMapping("/security")
public class SecurityController {
    @Autowired
    private UserService userService;

    @RequiresRoles("admin")
    @GetMapping("/list")
    public RestInfo getUserList() {
        List<UserEntity> list = userService.findAll();
        return new RestInfo<>(list);
    }

    @RequiresPermissions("user:read")
    @GetMapping("/get/{id}")
    public RestInfo getUser(@PathVariable("id") Long id) {
        UserEntity entity = userService.findById(id);
        return new RestInfo<>(entity);
    }

    @RequiresPermissions("user:save")
    @PostMapping("/save")
    public RestInfo save(@RequestBody @Validated UserEntity userEntity) {
        return new RestInfo<>(userService.save(userEntity));
    }

    @RequiresPermissions("user:delete")
    @PostMapping("/delete/{id}")
    public RestInfo delete(@PathVariable Long id) {
        userService.deleteById(id);
        return new RestInfo();
    }

    @RequiresPermissions("user:read")
    @GetMapping("/page")
    public RestInfo page(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                         @RequestParam(value = "size", required = false, defaultValue = "10") Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<UserEntity> list = userService.pageUser(pageable);
        return new RestInfo<>(list);
    }
}
