package com.xyb.domain.repository;

import com.xyb.domain.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @Author lian
 * @Date 2018/3/20
 */

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByName(String name);

    UserEntity findByNameAndAge(String name, Integer age);

    @Query("from UserEntity u where u.name=:name")
    UserEntity findUser(@Param("name") String name);
}
