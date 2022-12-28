package com.ljz.back.security;

import com.ljz.back.common.enums.StatusCode;
import com.ljz.back.domain.model.LoginUser;
import com.ljz.back.mapper.UserMapper;
import com.ljz.back.pojo.User;
import com.ljz.back.service.PermissionService;
import com.ljz.back.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * <p> 自定义 UserDetailsService 类 </p>
 *
 * @Author : ljz
 * @Date: 2022/12/12  10:58
 */

@Component
@Slf4j
public class CustomerUserDetailsService implements UserDetailsService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private PermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectUserByUserName(username);
        if (ObjectUtils.isEmpty(user)) {
            log.info("登录用户: {} 不存在", username);
            throw new RuntimeException("登录用户: " + username + "不存在");
        } else if (StatusCode.STOP.getCode().equals(user.getIsDeleted())) {
            log.info("登录用户: {} 已被删除", username);
            throw new RuntimeException("登录用户: " + username + "已被删除");
        } else if (StatusCode.STOP.getCode().equals(user.getStatus())) {
            log.info("登录用户: {} 已被停用", username);
            throw new RuntimeException("对不起, 您的账号: " + username + " 已停用");
        }
        return createLoginUser(user);
    }

    private UserDetails createLoginUser(User user) {
        return new LoginUser().setUserId(user.getUserId())
                .setUser(user)
                .setPermissions(permissionService.getMenuPermission(user));
    }
}
