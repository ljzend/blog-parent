package com.ljz.back.handler;

import com.ljz.back.common.ResultCode;

/**
 * <p></p>
 *
 * @Author : ljz
 * @Date: 2022/12/12  11:40
 */

public class BusinessException extends RuntimeException {

    private final int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.code = resultCode.getCode();
    }

    public BusinessException(ResultCode resultCode, String message) {
        super(message);
        this.code = resultCode.getCode();
    }

    public int getCode() {
        return code;
    }
}
