package com.ljz.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljz.back.mapper.RoleMenuMapper;
import com.ljz.back.pojo.RoleMenu;
import com.ljz.back.service.RoleMenuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色和菜单关联表 服务实现类
 * </p>
 *
 * @author ljz
 * @since 2022-12-22 09-43-46
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {
    /**
     * <p> 查询菜单分配给角色的个数 </p>
     * @param id 菜单id
     * @return 拥有该菜单的角色的个数
     */
    @Override
    public long checkMenuExistRole(Long id) {
        LambdaQueryWrapper<RoleMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RoleMenu::getMenuId, id);
        return count(wrapper);
    }
}
