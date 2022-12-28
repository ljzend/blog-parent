package com.ljz.back.domain.dto;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Map;

/**
 * <p> 接收前端传递查询用户信息的数据 </p>
 *
 * @Author : ljz
 * @Date: 2022/12/16  16:46
 */

@Data
@ApiModel("查询用户信息的条件")
@ToString
public class QueryUserDTO {
    @ApiModelProperty("用户ID")
    private Long userId;

    @ApiModelProperty("用户账号")
    private String userName;

    @ApiModelProperty("用户昵称")
    private String nickName;

    @ApiModelProperty("用户邮箱")
    private String email;

    @ApiModelProperty("手机号码")
    private String phone;

    @ApiModelProperty("帐号状态（0正常 1停用）")
    private String status;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("查询的页数")
    private Integer pageNo = 1;

    @ApiModelProperty("每页的条数")
    private Integer pageSize = 10;

    @ApiModelProperty("请求参数")
    private Map<String, Object> params;
}
