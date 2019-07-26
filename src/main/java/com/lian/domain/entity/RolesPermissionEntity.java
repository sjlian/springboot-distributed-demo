package com.lian.domain.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "t_roles_permission")
public class RolesPermissionEntity {
    @Id
    @Column(name = "role_name")
    @ApiModelProperty(value = "角色名",allowEmptyValue = false,readOnly=false)
    @NotNull(message = "角色名不能为空")
    private String roleName;

    @Column(name = "permission")
    @ApiModelProperty(value = "角色权限",allowEmptyValue = true,readOnly=false)
    @NotNull(message = "角色权限")
    private String permission;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
