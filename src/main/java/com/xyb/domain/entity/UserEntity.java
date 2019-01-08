package com.xyb.domain.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.annotation.Generated;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @Author lian
 * @Date 2018/3/20
 */
@Entity
@Table(name = "t_user")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}