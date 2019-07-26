package com.lian.service.impl;

import com.lian.common.util.LogUtil;
import com.lian.domain.entity.UserEntity;
import com.lian.domain.repository.UserRepository;
import com.lian.response.MyException;
import com.lian.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    public UserEntity save(UserEntity entity) {
        return userRepository.save(entity);
    }

    @Override
    @CacheEvict(cacheNames = "entity", key = "'user_' + #entity.id")
    public void delete(UserEntity entity) {
        userRepository.delete(entity);
    }

    @Override
    @CacheEvict(cacheNames = "entity", key = "'user_' + #id")
    public void deleteById(Long id) {
        if (id == 1) {
            throw MyException.notAllowed(null, "不允许删除");
        }
        userRepository.deleteById(id);
    }

    @Override
    @Cacheable(cacheNames = "entity", key = "'user_' + #id")
    public UserEntity findById(Long id) {
        LogUtil.info("从数据库里获取数据" + id);
        Optional<UserEntity> optional = userRepository.findById(id);
        return optional.orElseGet(UserEntity::new);

    }

    @Override
    public Page<UserEntity> pageUser(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * 事务控制，传播属性，隔离级别，超时，只读属性
     *
     * @param entity
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = -1, readOnly = false)
    @CacheEvict(cacheNames = "entity", key = "'user_' + #entity.id", beforeInvocation = true)
    @Override
    public void deleteWithTransaction(UserEntity entity) {
        if (entity == null || entity.getId() == null) {
            throw MyException.inputError(null, "user id not allow null");
        }
        userRepository.delete(entity);
        throw MyException.notAllowed(null, "这里手动抛出一个异常，以测试事务回滚");
    }


    @Override
    @CacheEvict(cacheNames = "entity", key = "'user_' + #entity.id")
    public UserEntity update(UserEntity entity) {
        return userRepository.save(entity);
    }
}
