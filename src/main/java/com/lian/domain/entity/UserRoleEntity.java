package com.lian.domain.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "t_user_roles")
@Setter
@Getter
public class UserRoleEntity {
    @Id
    @Column(name = "username")
    @ApiModelProperty(value = "用户名",allowEmptyValue = false,readOnly=false)
    @NotNull(message = "用户名不能为空")
    private String username;

    @Column(name = "role_name")
    @ApiModelProperty(value = "角色名",allowEmptyValue = false,readOnly=false)
    @NotNull(message = "角色名不能为空")
    private String roleName;
}
