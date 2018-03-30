package com.xyb.service;

import com.xyb.domain.entity.UserEntity;

import java.util.List;
import java.util.Optional;

/**
 * @Author lian
 * @Date 2018/3/20
 */

public interface UserService {
    List<UserEntity> findAll();

    UserEntity save(UserEntity entity);

    void delete(UserEntity entity);

    void deleteById(Long id);

    UserEntity findById(Long id);

    UserEntity update(UserEntity entity);
}
