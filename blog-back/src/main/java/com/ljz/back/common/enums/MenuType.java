package com.ljz.back.common.enums;

/**
 * <p>菜单类型（M目录 C菜单 F按钮）</p>
 *
 * @Author : ljz
 * @Date: 2022/12/23  11:06
 */
public enum MenuType {
    M("M", "目录"),
    C("C", "菜单"),
    F("F", "按钮");
    private final String code;
    private final String info;

    public String getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }

    MenuType(String code, String info) {
        this.code = code;
        this.info = info;
    }
}
