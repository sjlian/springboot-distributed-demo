package com.xyb.service.impl;

import com.xyb.domain.entity.RolesPermissionEntity;
import com.xyb.domain.entity.UserRoleEntity;
import com.xyb.domain.repository.RolesPermissionRepository;
import com.xyb.domain.repository.UserRoleRepository;
import com.xyb.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author lian
 * @Date 2018/3/29
 */
@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRoleRepository userRoleRepository;
    @Autowired
    private RolesPermissionRepository rolesPermissionRepository;
    @Override
    public List<RolesPermissionEntity> getRolesPermissionByRole(String roleName) {
        return rolesPermissionRepository.findByRoleName(roleName);
    }

    @Override
    public List<UserRoleEntity> getUserRoleByUser(String username) {
        return userRoleRepository.findByUsername(username);
    }
}
