package com.xyb.domain.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "t_user_roles")
public class UserRoleEntity {
    @Column(name = "username")
    @ApiModelProperty(value = "用户名",allowEmptyValue = false,readOnly=false)
    @NotNull(message = "用户名不能为空")
    private String username;

    @Column(name = "role_name")
    @ApiModelProperty(value = "角色名",allowEmptyValue = false,readOnly=false)
    @NotNull(message = "角色名不能为空")
    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
