package com.lian.common.shiro;

import com.lian.domain.entity.RolesPermissionEntity;
import com.lian.domain.entity.UserEntity;
import com.lian.domain.entity.UserRoleEntity;
import com.lian.service.AuthService;
import com.lian.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataSourceShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if (principals == null) {
            throw new AuthorizationException("PrincipalCollection method argument cannot be null.");
        } else {
            String username = (String)this.getAvailablePrincipal(principals);
            UserEntity userEntity = userService.findByUsername(username);
            if (userEntity == null){
                throw new UnknownAccountException("No account found for user [" + username + "]");
            }
            List<UserRoleEntity> roleEntities = authService.getUserRoleByUser(username);
            if (roleEntities == null || roleEntities.size() <= 0){
                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(null);
                info.setStringPermissions(null);
                return info;
            }
            Set<String> roleNames = new HashSet<>();
            Set<String> permissions = new HashSet<>(32);
            for (UserRoleEntity roleEntity : roleEntities){
                if (roleEntity.getRoleName() != null){
                    roleNames.add(roleEntity.getRoleName());
                }
            }
            for (String role : roleNames){
                List<RolesPermissionEntity> permissionEntities = authService.getRolesPermissionByRole(role);
                for (RolesPermissionEntity permissionEntity : permissionEntities){
                    permissions.add(permissionEntity.getPermission());
                }
            }
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roleNames);
            info.setStringPermissions(permissions);
            return info;
        }
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String username = upToken.getUsername();
        if (username == null) {
            throw new AccountException("Null username are not allowed by this realm.");
        } else {
            UserEntity userEntity = userService.findByUsername(username);
            if (userEntity == null){
                throw new UnknownAccountException("No account found for user [" + username + "]");
            }
            String salt;
            String password;
            password = userEntity.getPassword();
            salt = userEntity.getSalt();

            if (password == null) {
                throw new UnknownAccountException("No password found for user [" + username + "]");
            }
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, password.toCharArray(), this.getName());
            if (salt != null) {
                info.setCredentialsSalt(ByteSource.Util.bytes(salt));
            }
            return info;
        }
    }
}


