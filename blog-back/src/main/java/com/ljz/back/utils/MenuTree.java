package com.ljz.back.utils;

import com.ljz.back.common.enums.MenuType;
import com.ljz.back.common.enums.StatusCode;
import com.ljz.back.domain.vo.MetaVo;
import com.ljz.back.domain.vo.RouterVo;
import com.ljz.back.pojo.Menu;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * <p>生成菜单树</p>
 *
 * @Author : ljz
 * @Date: 2022/12/15  19:36
 */

public class MenuTree {
    /**
     * <p> 生成前端的路由信息 </p>
     *
     * @param menuList 菜单列表
     * @return List<RouterVo>
     */
    public static List<RouterVo> createRouterTree(List<Menu> menuList) {
        List<RouterVo> routerList = new ArrayList<>();
        for (Menu menu : menuList) {
            RouterVo routerVo = new RouterVo();
            routerVo.setName(menu.getMenuName());
            routerVo.setPath("/" + menu.getPath());
            routerVo.setHidden(StatusCode.STOP.getCode().equals(menu.getVisible()));
            routerVo.setComponent("/" + menu.getComponent());
            routerVo.setMeta(new MetaVo(
                    menu.getMenuName(),
                    menu.getIcon(),
                    StatusCode.STOP.getCode().equals(menu.getIsCache()),
                    SecurityUtils.getLoginUser().getPermissions()));
            List<Menu> children = menu.getChildren();
            if (!children.isEmpty() && MenuType.M.getCode().equals(menu.getMenuType())) {
                routerVo.setAlwaysShow(true);
                routerVo.setRedirect("noRedirect");
                routerVo.setComponent("Layout");
                routerVo.setChildren(createRouterTree(children));
            }
            routerList.add(routerVo);
        }
        return routerList;
    }

    /**
     * <p> 生成前端菜单 </p>
     *
     * @param meuList  菜单列表
     * @param parentId 父Id
     * @return List<Menu>
     */
    public static List<Menu> createMenuTree(List<Menu> meuList, Long parentId) {
        //创建集合保存菜单
        List<Menu> menuList = new ArrayList<>();
        //如果menuList菜单列表不为空，则使用菜单列表，否则创建集合对象
        Optional.ofNullable(meuList).orElse(new ArrayList<>())
                .stream()
                .filter(item -> item != null && Objects.equals(item.getParentId(), parentId))
                .forEach(item -> {
                    //创建菜单权限对象
                    Menu menu = new Menu();
                    //复制属性
                    BeanUtils.copyProperties(item, menu);
                    //获取每一个item的下级菜单,递归生成菜单树
                    List<Menu> children = createMenuTree(meuList, item.getMenuId());
                    //设置子菜单
                    menu.setChildren(children);
                    //将菜单对象添加到集合
                    menuList.add(menu);
                });
        return menuList;
    }
}
