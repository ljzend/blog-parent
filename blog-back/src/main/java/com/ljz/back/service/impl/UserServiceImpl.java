package com.ljz.back.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ljz.back.common.Result;
import com.ljz.back.domain.dto.QueryUserDTO;
import com.ljz.back.domain.vo.PageVo;
import com.ljz.back.mapper.UserMapper;
import com.ljz.back.pojo.User;
import com.ljz.back.service.UserRoleService;
import com.ljz.back.service.UserService;
import com.ljz.back.utils.SecurityUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Arrays;


/**
 * <p>
 * 用户信息表 服务实现类
 * </p>
 *
 * @author ljz
 * @since 2022-12-22 09-43-46
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private UserRoleService userRoleService;
    @Resource
    private UserMapper userMapper;

    /**
     * <p> 获取所有用户信息 </p>
     *
     * @param queryUserDTO 查询条件
     * @return Result
     */
    @Override
    public Result listUser(QueryUserDTO queryUserDTO) {
        Page<User> page = new Page<>(queryUserDTO.getPageNo(), queryUserDTO.getPageSize());
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(!ObjectUtils.isEmpty(queryUserDTO.getUserName()), User::getUserName, queryUserDTO.getUserName());
        wrapper.like(!ObjectUtils.isEmpty(queryUserDTO.getPhone()), User::getPhone, queryUserDTO.getPhone());
        wrapper.like(!ObjectUtils.isEmpty(queryUserDTO.getEmail()), User::getEmail, queryUserDTO.getEmail());
        if (queryUserDTO.getParams() != null) {
            wrapper.between(
                    User::getCreateTime,
                    queryUserDTO.getParams().get("beginTime"),
                    queryUserDTO.getParams().get("endTime"));
        }
        Page<User> userPage = page(page, wrapper);
        // 封装分页查询返回结果
        PageVo<User> userPageVo = new PageVo<User>()
                .setRecords(userPage.getRecords())
                .setTotal(userPage.getTotal());
        return Result.ok().setData(userPageVo);
    }

    /**
     * <p> 添加用户 </p>
     *
     * @param user 用户信息
     * @return Result
     */
    // fixme: 待完善
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Result addUser(User user) {
        User checkUser = findUserByUserName(user.getUserName());
        if (!ObjectUtils.isEmpty(checkUser)) {
            return Result.error().setMessage("新增用户" + user.getUserName() + "失败, 该账户已经存在");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        try {
            save(user);
            userRoleService.insertUserRole(user);
        } catch (Exception e) {
            throw new RuntimeException("新增用户" + user.getUserName() + "失败");
        }
        return Result.ok().setMessage("新增用户" + user.getUserName() + "成功");
    }

    /**
     * <p> 根据用户名查询用户 </p>
     *
     * @param userName 用户名
     * @return User
     */
    @Override
    public User findUserByUserName(String userName) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserName, userName);
        return getOne(wrapper);
    }

    /**
     * <p> 删除用户 </p>
     *
     * @param userIds 用户ID
     * @return Result
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Result deleteUser(Long[] userIds) {
        if (ArrayUtils.contains(userIds, SecurityUtils.getLoginUser().getUserId())) {
            return Result.error().setMessage("当前用户不能删除");
        }
        try {
            removeBatchByIds(Arrays.asList(userIds));
            userRoleService.deleteUserRole(userIds);
        } catch (Exception e) {
            throw new RuntimeException("删除用户失败");
        }
        return Result.ok().setMessage("删除用户成功");
    }

    /**
     * <p> 更新用户 </p>
     *
     * @param user 用户
     * @return Result
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Result updateUser(User user) {
        Long userId = user.getUserId();
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.set(!ObjectUtils.isEmpty(user.getUserName()), "user_name", user.getUserName());
        wrapper.set(!ObjectUtils.isEmpty(user.getNickName()), "nick_name", user.getNickName());
        wrapper.set(!ObjectUtils.isEmpty(user.getEmail()), "email", user.getEmail());
        wrapper.set(!ObjectUtils.isEmpty(user.getPhone()), "phone", user.getPhone());
        wrapper.set(!ObjectUtils.isEmpty(user.getSex()), "sex", user.getSex());
        wrapper.set(!ObjectUtils.isEmpty(user.getAvatar()), "avatar", user.getAvatar());
        wrapper.set(!ObjectUtils.isEmpty(user.getPassword()), "password", user.getPassword());
        wrapper.set(!ObjectUtils.isEmpty(user.getStatus()), "status", user.getStatus());
        wrapper.set(!ObjectUtils.isEmpty(user.getRemark()), "remark", user.getRemark());
        wrapper.eq("user_id", user.getUserId());
        try {
            userRoleService.deleteUserRoleByUserId(userId);
            userRoleService.insertUserRole(user);
            update(wrapper);
        } catch (Exception e) {
            throw new RuntimeException("更新用户" + user.getUserName() + "失败");
        }
        return Result.ok().setMessage("更新用户" + user.getUserName() + "成功");
    }

    /**
     * <p> 更新用户状态 </p>
     *
     * @param user 用户信息
     * @return Result
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public Result changeStatus(User user) {
        try {
            UpdateWrapper<User> wrapper = new UpdateWrapper<>();
            wrapper.set("status", user.getStatus())
                    .eq("user_id", user.getUserId());
            update(new User(), wrapper);
        } catch (Exception e) {
            throw new RuntimeException("更新用户状态失败");
        }
        return Result.ok().setMessage("更新用户状态失败");
    }

    /**
     * <p> 重置密码 </p>
     *
     * @param user 用户信息
     * @return Result
     */
    @Override
    public Result resetPwd(User user) {
        try {
            UpdateWrapper<User> wrapper = new UpdateWrapper<>();
            wrapper.set("password", user.getPassword())
                    .eq("user_id", user.getUserId());
            update(new User(), wrapper);
        } catch (Exception e) {
            throw new RuntimeException("重置用户密码失败");
        }
        return Result.ok().setMessage("重置用户密码成功");
    }

    /**
     * <p> 根据用户名查询用户信息 </p>
     *
     * @param userName 用户名
     * @return 用户信息
     */
    @Override
    public User selectUserByUserName(String userName) {
        return userMapper.selectUserByUserName(userName);
    }
}
