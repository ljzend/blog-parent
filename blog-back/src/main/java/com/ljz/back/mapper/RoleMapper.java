package com.ljz.back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljz.back.pojo.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 角色信息表 Mapper 接口
 * </p>
 *
 * @author ljz
 * @since 2022-12-22 09-43-46
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * <p> 根据用户ID查询用户所拥有的角色 </p>
     * @param userId 用户Id
     * @return Result
     */
    List<Role> getRolesByUserId(Long userId);

    /**
     * <p> 获取所有的角色 </p>
     * @return List<Role>
     */
    List<Role> getAllRoles();
}
