package com.ljz.back.service;

import com.ljz.back.pojo.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Set;

/**
 * <p>权限类</p>
 *
 * @Author : ljz
 * @Date: 2022/12/22  15:41
 */
public interface PermissionService {
    /**
     * <p> 获取用户的菜单权限 </p>
     * @param user 用户
     * @return 菜单权限
     */
    Set<String> getMenuPermission(User user);
}
