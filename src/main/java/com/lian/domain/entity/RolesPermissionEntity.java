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
@Table(name = "t_roles_permission")
@Setter
@Getter
public class RolesPermissionEntity {
    @Id
    @Column(name = "role_name")
    @ApiModelProperty(value = "角色名")
    @NotNull(message = "角色名不能为空")
    private String roleName;

    @Column(name = "permission")
    @ApiModelProperty(value = "角色权限",allowEmptyValue = true)
    @NotNull(message = "角色权限")
    private String permission;
}
