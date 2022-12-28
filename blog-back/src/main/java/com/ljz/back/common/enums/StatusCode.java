package com.ljz.back.common.enums;

/**
 * <p>通用状态编码</p>
 *
 * @Author : ljz
 * @Date: 2022/12/22  14:55
 */
public enum StatusCode {
    NORMAL("0", "正常"),
    STOP("1", "停用或标志删除");

    private final String code;
    private final String info;

    StatusCode(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
