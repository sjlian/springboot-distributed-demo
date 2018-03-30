package com.xyb.service.impl;

import com.xyb.domain.entity.UserEntity;
import com.xyb.domain.repository.UserRepository;
import com.xyb.exception.MyException;
import com.xyb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author lian
 * @Date 2018/3/20
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Cacheable(cacheNames = "entity",key = "'user_' + #id")
    public UserEntity save(UserEntity entity) {
        return userRepository.save(entity);
    }

    @Override
    @CacheEvict(cacheNames = "entity",key = "'user_' + #entity.id")
    public void delete(UserEntity entity) {
        userRepository.delete(entity);
    }

    @Override
    @CacheEvict(cacheNames = "entity",key = "'user_' + #id")
    public void deleteById(Long id) {
        if (id == 1){
            throw MyException.notAllowed(null,"不允许删除");
        }
        userRepository.deleteById(id);
    }

    @Override
    @Cacheable(cacheNames = "entity",key = "'user_' + #id")
    public UserEntity findById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    @CacheEvict(cacheNames = "entity",key = "'user_' + #entity.id")
    public UserEntity update(UserEntity entity) {
        return userRepository.save(entity);
    }
}
