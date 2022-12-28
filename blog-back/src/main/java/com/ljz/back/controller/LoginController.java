package com.ljz.back.controller;

import com.ljz.back.common.Result;
import com.ljz.back.domain.dto.LoginUserDTO;
import com.ljz.back.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>登录控制器</p>
 *
 * @Author : ljz
 * @Date: 2022/12/22  10:49
 */
@RestController
@RequestMapping("/api")
@Api(tags = "登录控制类(LoginController)")
@Slf4j
public class LoginController {
    @Resource
    private LoginService loginService;

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Result login(@RequestBody LoginUserDTO loginUserDTO) {
        log.info("登录用户为: {}", loginUserDTO);
        return loginService.login(loginUserDTO);
    }

    @GetMapping("/info")
    @ApiOperation("获取登录用户信息")
    public Result getInfo() {
        log.info("获取登录用户信息");
        return loginService.getLoginUserInfo();
    }

    @PostMapping("/logout")
    @ApiOperation("用户退出登录")
    public Result logout(HttpServletRequest request, HttpServletResponse response) {
        log.info("用户退出登录");
        return loginService.logout(request, response);
    }

    @GetMapping("/getRouters")
    @ApiOperation("获取登录用户的路由信息")
    public Result getRouters() {
        log.info("获取登录用户的路由信息");
        return loginService.getRouters();
    }
}
