package com.ljz.back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljz.back.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户信息表 Mapper 接口
 * </p>
 *
 * @author ljz
 * @since 2022-12-22 09-43-46
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * <p> 根据用户名查询用户信息 </p>
     * @param userName 用户名
     * @return 用户信息
     */
    User selectUserByUserName(String userName);

    /**
     * <p> 根据用户ID查询用户信息, 包括角色信息 </p>
     * @param userId 用户ID
     * @return User
     */
    User selectUserById(Long userId);
}
