package com.ljz.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljz.back.common.Result;
import com.ljz.back.domain.dto.QueryMenuDTO;
import com.ljz.back.mapper.MenuMapper;
import com.ljz.back.pojo.Menu;
import com.ljz.back.service.MenuService;
import com.ljz.back.service.RoleMenuService;
import com.ljz.back.utils.MenuTree;
import com.ljz.back.utils.SecurityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author ljz
 * @since 2022-12-22 09-43-45
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
    @Resource
    private MenuMapper menuMapper;
    @Resource
    private RoleMenuService roleMenuService;

    /**
     * <p> 加载对应角色菜单列表树 </p>
     *
     * @param id 角色Id
     * @return Result
     */
    @Override
    public Result roleMenuTreeSelect(Long id) {
        List<Menu> menuList = list();

        return null;
    }

    /**
     * <p> 获取所有菜单信息 </p>
     *
     * @param queryMenuDTO 查询条件
     * @return Result
     */
    @Override
    public Result listMenu(QueryMenuDTO queryMenuDTO) {
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(!ObjectUtils.isEmpty(queryMenuDTO.getMenuName()), Menu::getMenuName, queryMenuDTO.getMenuName());
        List<Menu> menuList = list(wrapper);
        List<Menu> menuTree = new ArrayList<>();
        if (!menuList.isEmpty()) {
            menuTree = MenuTree.createMenuTree(menuList,
                    menuList.stream().mapToLong(Menu::getParentId).min().getAsLong());
        }
        return Result.ok().setData(menuTree);
    }

    /**
     * <p> 添加菜单 </p>
     *
     * @param menu 菜单信息
     * @return Result
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Result addMenu(Menu menu) {
        if (checkMenuNameUnique(menu)) {
            return Result.error().setMessage("修改菜单'" + menu.getMenuName() + "'失败, 菜单名称已存在");
        }
        save(menu);
        return Result.ok().setMessage("新增菜单" + menu.getMenuName() + "成功");
    }

    /**
     * <p> 删除菜单 </p>
     *
     * @param id id
     * @return Result
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Result deleteMenu(Long id) {
        if (hasChildByMenuId(id) > 0L) {
            return Result.error().setMessage("存在子菜单,不允许删除");
        }
        if (roleMenuService.checkMenuExistRole(id) > 0L) {
            return Result.error().setMessage("菜单已分配给角色,不允许删除");
        }
        removeById(id);
        return Result.ok().message("删除菜单成功");
    }

    /**
     * <p> 查询菜单下子菜单的个数 </p>
     *
     * @param id 菜单id
     * @return 子菜单个数
     */
    private long hasChildByMenuId(Long id) {
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Menu::getParentId, id);
        return count(wrapper);
    }

    /**
     * <p> 更新菜单 </p>
     *
     * @param menu 菜单信息
     * @return Result
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Result updateMenu(Menu menu) {
        if (checkMenuNameUnique(menu)) {
            return Result.error().setMessage("修改菜单'" + menu.getMenuName() + "'失败, 菜单名称已存在");
        }
        if (menu.getMenuId().equals(menu.getParentId())) {
            return Result.error().setMessage("修改菜单'" + menu.getMenuName() + "'失败, 上级菜单不能选择自己");
        }
        updateById(menu);
        return Result.ok().message("更新菜单成功");
    }

    /**
     * <p> 根据角色ID查询菜单权限 </p>
     *
     * @param roleId 角色ID
     * @return 菜单权限
     */
    @Override
    public Set<String> selectMenuPermsByRoleId(Long roleId) {
        List<String> perms = menuMapper.selectMenuPermsByRoleId(roleId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotBlank(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * <p> 根据用户ID查询菜单权限 </p>
     * @param userId 用户ID
     * @return 菜单权限
     */
    @Override
    public Set<String> selectMenuPermsByUserId(Long userId) {
        List<String> perms = menuMapper.selectMenuPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNotBlank(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    /**
     * <p> 根据用户ID查询菜单, 并转换为菜单树 </p>
     * @param userId 用户ID
     * @return List<Menu>
     */
    @Override
    public List<Menu> selectMenuTreeByUserId(Long userId) {
        List<Menu> menus;
        if(SecurityUtils.isAdmin(userId)){
            menus = menuMapper.selectMenuTreeAll();
        }else{
            menus = menuMapper.selectMenuTreeByUserId(userId);
        }
        return MenuTree.createMenuTree(menus, 0L);
    }

    /**
     * <p> 判断菜单名是否唯一 </p>
     *
     * @param menu 菜单
     * @return true: 唯一  false: 不唯一
     */
    private boolean checkMenuNameUnique(Menu menu) {
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Menu::getMenuName, menu.getMenuName())
                .eq(Menu::getParentId, menu.getParentId());
        return !ObjectUtils.isEmpty(getOne(wrapper));
    }
}
