package com.ljz.back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljz.back.pojo.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author ljz
 * @since 2022-12-22 09-43-45
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * <p> 根据角色ID查询菜单权限 </p>
     * @param roleId 角色ID
     * @return 菜单权限
     */
    List<String> selectMenuPermsByRoleId(Long roleId);

    /**
     * <p> 根据用户ID查询菜单权限 </p>
     * @param userId 用户ID
     * @return 菜单权限
     */
    List<String> selectMenuPermsByUserId(Long userId);

    /**
     * <p> 查询所有菜单 </p>
     * @return List<Menu>
     */
    List<Menu> selectMenuTreeAll();

    /**
     * <p> 根据用户ID查询菜单 </p>
     * @param userId 用户ID
     * @return List<Menu>
     */
    List<Menu> selectMenuTreeByUserId(Long userId);
}
