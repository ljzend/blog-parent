package com.ljz.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljz.back.pojo.User;
import com.ljz.back.pojo.UserRole;

/**
 * <p>
 * 用户和角色关联表 服务类
 * </p>
 *
 * @author ljz
 * @since 2022-12-22 09-43-46
 */
public interface UserRoleService extends IService<UserRole> {
    /**
     * <p> 根据用户 Id 删除角色用户关联表中的数据</p>
     * @param userIds 用户 Id
     */
    void deleteUserRole(Long[] userIds);

    /**
     * <p> 根据角色id 查询用户和角色关系表中，是否有用户在使用改角色</p>
     * @param id 角色Id
     * @return Long
     */
    Long getCountByRoleId(Long id);

    /**
     * <p> 新增用户和角色关系 </p>
     * @param user 用户
     */
    void insertUserRole(User user);

    /**
     * <p> 根据用户ID删除角色关系 </p>
     * @param userId 用户ID
     */
    void deleteUserRoleByUserId(Long userId);
}
