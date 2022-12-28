package com.ljz.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljz.back.common.Result;
import com.ljz.back.domain.dto.QueryRoleDTO;
import com.ljz.back.pojo.Role;

/**
 * <p>
 * 角色信息表 服务类
 * </p>
 *
 * @author ljz
 * @since 2022-12-22 09-43-46
 */
public interface RoleService extends IService<Role> {
    /**
     * <p> 获取所有角色 </p>
     * @param queryRoleDTO 查询条件
     * @return Result
     */
    Result listRole(QueryRoleDTO queryRoleDTO);

    /**
     * <p> 添加角色 </p>
     * @param role 角色
     * @return Result
     */
    Result addRole(Role role);

    /**
     * <p> 删除角色 </p>
     * @param id 角色id
     * @return Result
     */
    Result deleteRole(Long id);

    /**
     * <p> 更新角色 </p>
     * @param role 角色信息
     * @return Result
     */
    Result updateRole(Role role);

    /**
     * <p> 根据用户ID查询用户所拥有的角色 </p>
     * @param userId 用户Id
     * @return Result
     */
    Result getRolesByUserId(Long userId);
}
