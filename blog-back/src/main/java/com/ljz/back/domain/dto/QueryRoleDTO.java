package com.ljz.back.domain.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;
import java.util.Map;

/**
 * <p>接收前端传递查询角色信息的数据</p>
 *
 * @Author : ljz
 * @Date: 2022/12/17  21:10
 */
@Data
@ApiModel("查询角色信息的条件")
@ToString
public class QueryRoleDTO {

    @ApiModelProperty("角色编码")
    private String roleKey;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("角色状态（0正常 1停用）")
    private String status;

    @ApiModelProperty("查询的页数")
    private Integer pageNo = 1;

    @ApiModelProperty("每页的条数")
    private Integer pageSize = 10;

    @ApiModelProperty("请求参数")
    private Map<String, Object> params;
}
