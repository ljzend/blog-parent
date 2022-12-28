package com.ljz.back.controller;

import com.ljz.back.common.Result;
import com.ljz.back.domain.dto.LoginUserDTO;
import com.ljz.back.domain.dto.QueryUserDTO;
import com.ljz.back.pojo.User;
import com.ljz.back.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * <p>
 * 用户信息表 前端控制器
 * </p>
 *
 * @author ljz
 * @since 2022-12-22 09-43-46
 */
@RestController
@RequestMapping("/api/user")
@Api(tags = "用户控制类(UserController)")
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/list")
    @ApiOperation("获取所有用户信息")
    public Result listUser(QueryUserDTO queryUserDTO) {
        log.info("获取所有用户信息,请求参数为 ==> {}", queryUserDTO);
        return userService.listUser(queryUserDTO);
    }

    @PostMapping("/add")
    @ApiOperation("添加用户")
    public Result addUser(@RequestBody User user) {
        log.info("添加用户,请求参数为 ==> {}", user);
        return userService.addUser(user);
    }

    @DeleteMapping("/delete/{userIds}")
    @ApiOperation("删除用户")
    public Result deleteUser(@PathVariable Long[] userIds) {
        log.info("删除用户,请求参数为 ==> {}", Arrays.toString(userIds));
        return userService.deleteUser(userIds);
    }

    @PutMapping("/update")
    @ApiOperation("更新用户")
    public Result updateUser(@RequestBody User user){
        log.info("更新用户,请求参数为 ==> {}", user);
        return userService.updateUser(user);
    }

    @PutMapping("/changeStatus")
    @ApiOperation("用户状态修改")
    public Result changeStatus(@RequestBody User user){
        log.info("用户状态修改,请求参数为 ==> {}", user);
        return userService.changeStatus(user);
    }

    @PutMapping("/resetPwd")
    @ApiOperation("用户状态修改")
    public Result resetPwd(@RequestBody User user){
        log.info("用户状态修改,请求参数为 ==> {}", user);
        return userService.resetPwd(user);
    }

    @GetMapping("/get/{userId}")
    @ApiOperation("根据id查询用户信息")
    public Result getUser(@PathVariable Long userId){
        log.info("根据id查询用户信息,请求参数为 ==> {}", userId);
        return Result.ok().setData(userService.getById(userId));
    }
}
