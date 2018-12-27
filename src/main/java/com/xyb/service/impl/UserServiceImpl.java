package com.xyb.service.impl;

import com.xyb.common.util.LogUtil;
import com.xyb.domain.entity.UserEntity;
import com.xyb.domain.repository.UserRepository;
import com.xyb.response.MyException;
import com.xyb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
        LogUtil.info("从数据库里获取数据" + id);
        return userRepository.findById(id).get();
    }

    @Override
    public Page<UserEntity> pageUser(Pageable pageable) {
        return userRepository.findAll(pageable);
    }


    @Override
    @CacheEvict(cacheNames = "entity",key = "'user_' + #entity.id")
    public UserEntity update(UserEntity entity) {
        return userRepository.save(entity);
    }
}
