package com.ljz.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljz.back.mapper.UserRoleMapper;
import com.ljz.back.pojo.User;
import com.ljz.back.pojo.UserRole;
import com.ljz.back.service.UserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * <p>
 * 用户和角色关联表 服务实现类
 * </p>
 *
 * @author ljz
 * @since 2022-12-22 09-43-46
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
    /**
     * <p> 根据用户 Id 删除角色用户关联表中的数据</p>
     * @param userIds 用户 Id
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void deleteUserRole(Long[] userIds) {
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(UserRole::getUserId, Arrays.asList(userIds));
        remove(wrapper);
    }

    /**
     * <p> 根据角色id 查询用户和角色关系表中，是否有用户在使用改角色</p>
     * @param id 角色Id
     * @return Long
     */
    @Override
    public Long getCountByRoleId(Long id) {
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getRoleId, id);
        return count(wrapper);
    }

    /**
     * <p> 新增用户和角色关系 </p>
     * @param user 用户
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void insertUserRole(User user) {
        Long[] roleIds = user.getRoleIds();
        ArrayList<UserRole> userRoles = new ArrayList<>();
        for(Long roleId : roleIds) {
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getUserId());
            userRole.setRoleId(roleId);
            userRoles.add(userRole);
        }
        saveBatch(userRoles);
    }

    /**
     * <p> 根据用户ID删除角色关系 </p>
     * @param userId 用户ID
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void deleteUserRoleByUserId(Long userId) {
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId, userId);
        remove(wrapper);
    }
}
