package com.ljz.back.utils;

import com.ljz.back.domain.model.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;

/**
 * <p>获取Authentication工具类</p>
 *
 * @Author : ljz
 * @Date: 2022/12/22  15:03
 */

public class SecurityUtils {
    /**
     * <p> 从 SecurityContextHolder 中获取登录用户信息</p>
     * @return 登录用户信息
     */
    public static LoginUser getLoginUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (ObjectUtils.isEmpty(authentication)) {
            throw new RuntimeException("authentication获取失败");
        }
        return (LoginUser)authentication.getPrincipal();
    }

    /**
     * <p> 判断用户是否是超级管理员 </p>
     * @param userId 用户ID
     * @return boolean
     */
    public static boolean isAdmin(Long userId){
        return userId != null && 1L == userId;
    }
}
