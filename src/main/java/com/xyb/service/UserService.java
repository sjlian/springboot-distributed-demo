package com.xyb.service;

import com.xyb.domain.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

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

    Page<UserEntity> pageUser(Pageable pageable);

    UserEntity findByUsername(String username);

    void deleteWithTransaction(UserEntity entity);

}
