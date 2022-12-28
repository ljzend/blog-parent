package com.ljz.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljz.back.common.Result;
import com.ljz.back.domain.dto.QueryMenuDTO;
import com.ljz.back.pojo.Menu;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author ljz
 * @since 2022-12-22 09-43-45
 */
public interface MenuService extends IService<Menu> {
    /**
     * <p> 加载对应角色菜单列表树 </p>
     * @param id 角色Id
     * @return Result
     */
    Result roleMenuTreeSelect(Long id);

    /**
     * <p> 获取所有菜单信息 </p>
     * @param queryMenuDTO 查询条件
     * @return Result
     */
    Result listMenu(QueryMenuDTO queryMenuDTO);

    /**
     * <p> 添加菜单 </p>
     * @param menu 菜单信息
     * @return Result
     */
    Result addMenu(Menu menu);

    /**
     * <p> 删除菜单 </p>
     * @param id id
     * @return Result
     */
    Result deleteMenu(Long id);

    /**
     * <p>  更新菜单 </p>
     * @param menu 菜单信息
     * @return Result
     */
    Result updateMenu(Menu menu);

    /**
     * <p> 根据角色ID查询菜单权限 </p>
     * @param roleId 角色ID
     * @return 菜单权限
     */
    Set<String> selectMenuPermsByRoleId(Long roleId);

    /**
     * <p> 根据用户ID查询菜单权限 </p>
     * @param userId 用户ID
     * @return 菜单权限
     */
    Set<String> selectMenuPermsByUserId(Long userId);

    /**
     * <p> 根据用户ID查询菜单 </p>
     * @param userId 用户ID
     * @return List<Menu>
     */
    List<Menu> selectMenuTreeByUserId(Long userId);
}
