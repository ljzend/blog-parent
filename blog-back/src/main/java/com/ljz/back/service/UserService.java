package com.ljz.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljz.back.common.Result;
import com.ljz.back.domain.dto.LoginUserDTO;
import com.ljz.back.domain.dto.QueryUserDTO;
import com.ljz.back.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 用户信息表 服务类
 * </p>
 *
 * @author ljz
 * @since 2022-12-22 09-43-46
 */
public interface UserService extends IService<User> {
    /**
     * <p> 获取所有用户信息 </p>
     * @param queryUserDTO 查询条件
     * @return Result
     */
    Result listUser(QueryUserDTO queryUserDTO);

    /**
     * <p> 添加用户 </p>
     * @param user 用户信息
     * @return Result
     */
    Result addUser(User user);

    /**
     * <p> 删除用户 </p>
     * @param userIds 用户ID
     * @return Result
     */
    Result deleteUser(Long[] userIds);

    /**
     * <p> 更新用户 </p>
     * @param user 用户
     * @return Result
     */
    Result updateUser(User user);

    /**
     * <p> 更新用户状态 </p>
     * @param user 用户信息
     * @return Result
     */
    Result changeStatus(User user);

    /**
     * <p> 重置密码 </p>
     * @param user 用户信息
     * @return Result
     */
    Result resetPwd(User user);

    /**
     * <p> 根据用户名查询用户信息 </p>
     * @param username 用户名
     * @return 用户信息
     */
    User selectUserByUserName(String username);

    /**
     * <p> 根据用户名查询用户 </p>
     * @param userName 用户名
     * @return User
     */
    User findUserByUserName(String userName);
}
