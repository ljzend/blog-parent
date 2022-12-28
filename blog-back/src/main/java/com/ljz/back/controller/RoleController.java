package com.ljz.back.controller;

import com.ljz.back.common.Result;
import com.ljz.back.domain.dto.QueryRoleDTO;
import com.ljz.back.pojo.Role;
import com.ljz.back.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 角色信息表 前端控制器
 * </p>
 *
 * @author ljz
 * @since 2022-12-22 09-43-46
 */
@RestController
@RequestMapping("/api/role")
@Api(tags = "角色控制类(RoleController)")
@Slf4j
public class RoleController {
    @Resource
    private RoleService roleService;

    @ApiOperation("获取所有角色")
    @GetMapping("/list")
    public Result listRole(QueryRoleDTO queryRoleDTO) {
        log.info("获取所有角色信息, 请求参数为 ==> {}", queryRoleDTO);
        return roleService.listRole(queryRoleDTO);
    }

    @ApiOperation("添加角色")
    @PostMapping("/add")
    public Result addRole(@RequestBody Role role) {
        log.info("添加角色, 请求参数为 ==> {}", role);
        return roleService.addRole(role);
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/delete/{roleId}")
    public Result deleteRole(@PathVariable Long roleId) {
        log.info("删除角色, 请求参数为 ==> {}", roleId);
        return roleService.deleteRole(roleId);
    }

    @ApiOperation("更新角色")
    @PutMapping("/update")
    public Result updateRole(@RequestBody Role role) {
        log.info("更新角色, 请求参数为 ==> {}", role);
        return roleService.updateRole(role);
    }

    @ApiOperation("根据角色Id查询角色信息")
    @GetMapping("/get/{roleId}")
    public Result getRole(@PathVariable Long roleId) {
        log.info("根据角色Id查询角色信息, 请求参数为 ==> {}", roleId);
        return Result.ok().setData(roleService.getById(roleId));
    }

    @ApiOperation("根据用户ID查询用户所拥有的角色")
    @GetMapping(value = {"/getRolesByUserId/{userId}", "getRolesByUserId"})
    public Result getRolesByUserId(@PathVariable(value = "userId",required = false) Long userId){
        log.info("根据用户ID查询用户所拥有的角色, 请求参数为 ==> {}", userId);
        return roleService.getRolesByUserId(userId);
    }

}
