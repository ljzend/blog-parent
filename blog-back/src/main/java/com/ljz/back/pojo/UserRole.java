package com.ljz.back.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 用户和角色关联表
 * </p>
 *
 * @author ljz
 * @since 2022-12-22 09-43-46
 */
@Getter
@Setter
@TableName("sys_user_role")
@ApiModel(value = "UserRole对象", description = "用户和角色关联表")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @MppMultiId
    @ApiModelProperty("用户ID")
    @TableField("user_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    @MppMultiId
    @ApiModelProperty("角色ID")
    @TableField("role_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long roleId;


}
