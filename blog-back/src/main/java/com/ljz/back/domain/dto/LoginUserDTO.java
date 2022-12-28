package com.ljz.back.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p> 接收前台用户传递的登录参数</p>
 *
 * @Author : ljz
 * @Date: 2022/12/16  16:47
 */

@Data
public class LoginUserDTO {
    @ApiModelProperty("登录名称(用户名)")
    private String username;

    @ApiModelProperty("登录密码")
    private String password;
}
