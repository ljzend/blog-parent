package com.ljz.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljz.back.common.Result;
import com.ljz.back.domain.dto.QueryRoleDTO;
import com.ljz.back.domain.vo.PageVo;
import com.ljz.back.mapper.RoleMapper;
import com.ljz.back.mapper.UserMapper;
import com.ljz.back.pojo.Role;
import com.ljz.back.pojo.User;
import com.ljz.back.service.RoleService;
import com.ljz.back.service.UserRoleService;
import com.ljz.back.utils.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author ljz
 * @since 2022-12-22 09-43-46
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private UserMapper userMapper;

    /**
     * <p> 获取所有角色 </p>
     *
     * @param queryRoleDTO 查询条件
     * @return Result
     */
    @Override
    public Result listRole(QueryRoleDTO queryRoleDTO) {
        Page<Role> page = new Page<>(queryRoleDTO.getPageNo(), queryRoleDTO.getPageSize());
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(!ObjectUtils.isEmpty(queryRoleDTO.getRoleKey()), Role::getRoleKey, queryRoleDTO.getRoleKey());
        wrapper.like(!ObjectUtils.isEmpty(queryRoleDTO.getRoleName()), Role::getRoleName, queryRoleDTO.getRoleName());
        wrapper.like(!ObjectUtils.isEmpty(queryRoleDTO.getStatus()), Role::getStatus, queryRoleDTO.getStatus());
        if (queryRoleDTO.getParams() != null) {
            wrapper.between(
                    Role::getCreateTime,
                    queryRoleDTO.getParams().get("beginTime"),
                    queryRoleDTO.getParams().get("endTime"));
        }
        Page<Role> rolePage = page(page, wrapper);
        // 封装分页查询返回结果
        PageVo<Role> rolePageVo = new PageVo<Role>()
                    .setRecords(rolePage.getRecords())
                .setTotal(rolePage.getTotal());
        return Result.ok().setData(rolePageVo);
    }

    /**
     * <p> 添加角色 </p>
     *
     * @param role 角色
     * @return Result
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Result addRole(Role role) {
        save(role);
        return Result.ok().setMessage("添加角色" + role.getRoleName() + "成功");
    }

    /**
     * <p> 删除角色 </p>
     *
     * @param id 角色id
     * @return Result
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Result deleteRole(Long id) {
        Long count = userRoleService.getCountByRoleId(id);
        if (count > 0L) {
            return Result.error().setMessage("该角色用户在使用, 不能删除");
        }
        removeById(id);
        return Result.ok().setMessage("删除角色成功");
    }

    /**
     * <p> 更新角色 </p>
     *
     * @param role 角色信息
     * @return Result
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Result updateRole(Role role) {
        updateById(role);
        return Result.ok().setMessage("更新角色信息成功");
    }

    /**
     * <p> 根据用户ID查询用户所拥有的角色 </p>
     *
     * @param userId 用户Id
     * @return Result
     */
    @Override
    public Result getRolesByUserId(Long userId) {
        Map<String, Object> map = new HashMap<>();
        List<Role> roles = roleMapper.getAllRoles();
        roles = SecurityUtils.isAdmin(userId) ? roles : roles.stream().filter(item -> item.getRoleId() != 1L).collect(Collectors.toList());
        map.put("roles", roles);
        if(userId != null) {
            User user = userMapper.selectUserById(userId);
            System.out.println(user);
            List<Long> roleIds = user.getRoles().stream().map(Role::getRoleId).collect(Collectors.toList());
            map.put("user", user);
            map.put("roleIds", roleIds);
        }
        return Result.ok().setData(map);
    }
}
