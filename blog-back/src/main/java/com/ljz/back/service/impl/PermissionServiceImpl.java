package com.ljz.back.service.impl;

import com.ljz.back.pojo.Role;
import com.ljz.back.pojo.User;
import com.ljz.back.service.MenuService;
import com.ljz.back.service.PermissionService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>权限类</p>
 *
 * @Author : ljz
 * @Date: 2022/12/22  15:42
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private MenuService menuService;

    /**
     * <p> 获取用户的菜单权限 </p>
     *
     * @param user 用户
     * @return 菜单权限
     */
    @Override
    public Set<String> getMenuPermission(User user) {
        Set<String> perms = new HashSet<>();
        // 获取用户的角色
        List<Role> roles = user.getRoles();
        if (!roles.isEmpty() && roles.size() > 1) {
            // 多角色设置permissions属性，以便数据权限匹配权限
            for (Role role : roles) {
                Set<String> rolePerms = menuService.selectMenuPermsByRoleId(role.getRoleId());
                role.setPermissions(rolePerms);
                perms.addAll(rolePerms);
            }
        } else {
            perms.addAll(menuService.selectMenuPermsByUserId(user.getUserId()));
        }
        return perms;
    }
}
