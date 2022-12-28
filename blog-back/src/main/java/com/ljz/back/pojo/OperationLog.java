package com.ljz.back.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author ljz
 * @since 2022-12-12 09-52-03
 */
@Getter
@Setter
@TableName("sys_operation_log")
@ApiModel(value = "OperationLog对象", description = "")
public class OperationLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("操作用户名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty("操作名称")
    @TableField("operation_name")
    private String operationName;

    @ApiModelProperty("请求路径")
    @TableField("request_uri")
    private String requestUri;

    @ApiModelProperty("请求方式")
    @TableField("method_type")
    private String methodType;

    @ApiModelProperty("请求参数")
    @TableField("request_param")
    private String requestParam;

    @ApiModelProperty("方法名")
    @TableField("method_name")
    private String methodName;

    @ApiModelProperty("响应状态码")
    @TableField("code")
    private Integer code;

    @ApiModelProperty("返回参数")
    @TableField("response_param")
    private String responseParam;

    @ApiModelProperty("异常信息")
    @TableField("exception_msg")
    private String exceptionMsg;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


}
