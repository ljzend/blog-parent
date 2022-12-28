package com.ljz.back.domain.dto;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * <p>接收前端传递查询菜单信息的数据</p>
 *
 * @Author : ljz
 * @Date: 2022/12/21  15:10
 */

@Data
@ApiModel("查询菜单信息的条件")
@ToString
public class QueryMenuDTO {

    @ApiModelProperty("菜单ID")
    private Long menuId;

    @ApiModelProperty("菜单名称")
    private String menuName;

    @ApiModelProperty("父菜单ID")
    private Long parentId;

    @ApiModelProperty("显示顺序")
    private Integer orderNum;

    @ApiModelProperty("路由地址")
    @TableField("path")
    private String path;

    @ApiModelProperty("组件路径")
    private String component;

    @ApiModelProperty("菜单类型（M目录 C菜单 F按钮）")
    private String menuType;

    @ApiModelProperty("菜单状态（0显示 1隐藏）")
    private String visible;

    @ApiModelProperty("菜单状态（0正常 1停用）")
    private String status;

    @ApiModelProperty("权限标识")
    private String perms;

    @ApiModelProperty("菜单图标")
    private String icon;

    @ApiModelProperty("创建者")
    private String createBy;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("备注")
    private String remark;
}
