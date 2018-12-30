package com.xyb.service;

import com.xyb.domain.entity.RolesPermissionEntity;
import com.xyb.domain.entity.UserRoleEntity;

import java.util.List;

/**
 * @Author lian
 * @Date 2018/3/29
 */

public interface AuthService {
    List<RolesPermissionEntity> getRolesPermissionByRole(String roleName);

    List<UserRoleEntity> getUserRoleByUser(String username);
}
