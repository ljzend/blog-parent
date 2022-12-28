package com.ljz.back.service;

import com.ljz.back.common.Result;
import com.ljz.back.domain.dto.LoginUserDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p></p>
 *
 * @Author : ljz
 * @Date: 2022/12/22  11:03
 */
public interface LoginService {
    /**
     * <p> 用户登录 </p>
     * @param loginUserDTO 登录用户信息
     * @return Result
     */
    Result login(LoginUserDTO loginUserDTO);

    /**
     * <p> 获取登录用户信息 </p>
     * @return Result
     */
    Result getLoginUserInfo();

    /**
     * <p> 用户退出登录 </p>
     * @param request 请求对象
     * @param response 响应对象
     * @return Result
     */
    Result logout(HttpServletRequest request, HttpServletResponse response);

    /**
     * <p> 获取登录用户的路由信息 </p>
     * @return Result
     */
    Result getRouters();
}
