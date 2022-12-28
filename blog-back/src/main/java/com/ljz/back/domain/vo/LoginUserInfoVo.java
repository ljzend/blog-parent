package com.ljz.back.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * <p>封装登录用户信息</p>
 *
 * @Author : ljz
 * @Date: 2022/12/15  9:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@ApiModel("封装登录用户信息")
@ToString
public class LoginUserInfoVo {
    @ApiModelProperty("用户ID")
    private Long id;
    @ApiModelProperty("用户名称")
    private String name;
    @ApiModelProperty("头像")
    private String avatar;
    @ApiModelProperty("介绍")
    private String introduction;
    @ApiModelProperty("角色权限集合")
    private Set<String> roles;
}
