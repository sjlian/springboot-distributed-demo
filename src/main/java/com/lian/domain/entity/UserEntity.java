package com.lian.domain.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author lian
 * @Date 2018/3/20
 */
@Entity
@Table(name = "t_user")
@Setter
@Getter
public class UserEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("id")
    private Long id;

    @Column(name = "username")
    @ApiModelProperty(value = "用户名",allowEmptyValue = false,readOnly=false)
    @NotNull(message = "用户名不能为空")
    private String username;

    @Column(name = "password")
    @ApiModelProperty("密码")
    private String password;

    @Column(name = "salt")
    @ApiModelProperty("密码盐")
    private String salt;

    @Version
    @ApiModelProperty("乐观锁，version=version时才更新，同时version+1，jpa加上version注解后自动实现了")
    @Column(name = "version")
    private Long version;

    @CreatedDate
    private Date createdDate;
}