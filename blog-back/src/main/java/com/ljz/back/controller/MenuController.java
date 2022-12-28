package com.ljz.back.controller;

import com.ljz.back.common.Result;
import com.ljz.back.domain.dto.QueryMenuDTO;
import com.ljz.back.pojo.Menu;
import com.ljz.back.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author ljz
 * @since 2022-12-22 09-43-45
 */
@RestController
@RequestMapping("/api/menu")
@Api(tags = "菜单控制类(MenuController)")
@Slf4j
public class MenuController {
    @Resource
    private MenuService menuService;

    @GetMapping("/roleMenuTreeSelect/{id}")
    @ApiOperation("加载对应角色菜单列表树")
    public Result roleMenuTreeSelect(@PathVariable Long id){
        log.info("加载对应角色菜单列表树,请求参数为 ==> {}", id);
        return menuService.roleMenuTreeSelect(id);
    }

    @GetMapping("/list")
    @ApiOperation("获取所有菜单信息")
    public Result listMenu(QueryMenuDTO queryMenuDTO){
        log.info("获取所有菜单信息,请求参数为 ==> {}", queryMenuDTO);
        return menuService.listMenu(queryMenuDTO);
    }

    @PostMapping("/add")
    @ApiOperation("添加菜单")
    public Result addMenu(@RequestBody Menu menu) {
        log.info("添加菜单,请求参数为 ==> {}", menu);
        return menuService.addMenu(menu);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除菜单")
    public Result deleteMenu(@PathVariable("id") Long id) {
        log.info("删除菜单,请求参数为 ==> {}", id);
        return menuService.deleteMenu(id);
    }

    @PutMapping("/update")
    @ApiOperation("更新菜单")
    public Result updateMenu(@RequestBody Menu menu){
        log.info("更新菜单,请求参数为 ==> {}", menu);
        return menuService.updateMenu(menu);
    }

    @GetMapping("/get/{id}")
    @ApiOperation("根据id查询菜单信息")
    public Result getMenu(@PathVariable("id") Long id){
        log.info("根据id查询菜单信息,请求参数为 ==> {}", id);
        return Result.ok().setData(menuService.getById(id));
    }
}
