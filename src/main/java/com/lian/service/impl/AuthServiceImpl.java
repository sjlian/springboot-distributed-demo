package com.lian.service.impl;

import com.lian.domain.entity.RolesPermissionEntity;
import com.lian.domain.entity.UserRoleEntity;
import com.lian.domain.repository.RolesPermissionRepository;
import com.lian.domain.repository.UserRoleRepository;
import com.lian.service.AuthService;
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
