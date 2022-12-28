package com.ljz.back.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>统一响应结果</p>
 *
 * @Author : ljz
 * @Date: 2022/8/27  15:00
 */
@ApiModel("统一返回结果")
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {
    @ApiModelProperty("是否成功")
    private Boolean success;
    @ApiModelProperty("响应状态码")
    private Integer code;
    @ApiModelProperty("响应消息")
    private String message;
    @ApiModelProperty("返回数据")
    private T data;
    @ApiModelProperty("时间戳")
    private long timestamp;

    private Result() {
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 成功执行，不返回数据
     *
     * @return R
     */
    public static <T> Result<T> ok() {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        return result;
    }

    /**
     * 成功执行，并返回数据
     *
     * @param data 数据
     * @param <T> 类型
     * @return R
     */
    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<>();
        result.setSuccess(true);
        result.setCode(ResultCode.SUCCESS.getCode());
        result.setMessage(ResultCode.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    /**
     * 失败
     *
     * @return R
     */
    public static <T> Result<T> error() {
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(ResultCode.ERROR.getCode());
        result.setMessage(ResultCode.ERROR.getMessage());
        return result;
    }

    /**
     * 设置是否成功
     *
     * @param success 是否成功
     * @return R
     */
    public Result<T> success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    /**
     * 设置状态码
     *
     * @param code 状态码
     * @return R
     */
    public Result<T> code(Integer code) {
        this.setCode(code);
        return this;
    }

    /**
     * 设置返回消息
     *
     * @param message 返回消息
     * @return R
     */
    public Result<T> message(String message) {
        this.setMessage(message);
        return this;
    }
}