package com.ljz.back.common.enums;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <p>性别编码</p>
 *
 * @Author : ljz
 * @Date: 2022/12/22  14:49
 */

public enum SexCode {
    MALE("0", "男性"),
    FEMALE("1", "女性");

    private final String code;
    private final String info;

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    SexCode(String code, String info) {
        this.code = code;
        this.info = info;
    }
}
