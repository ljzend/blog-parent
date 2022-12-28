package com.ljz.back.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ljz.back.pojo.RoleMenu;

/**
 * <p>
 * 角色和菜单关联表 服务类
 * </p>
 *
 * @author ljz
 * @since 2022-12-22 09-43-46
 */
public interface RoleMenuService extends IService<RoleMenu> {
    /**
     * <p> 查询菜单分配给角色的个数 </p>
     * @param id 菜单id
     * @return 拥有该菜单的角色的个数
     */
    long checkMenuExistRole(Long id);
}
