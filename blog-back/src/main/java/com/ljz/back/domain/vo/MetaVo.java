package com.ljz.back.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

/**
 * <p></p>
 *
 * @Author : ljz
 * @Date: 2022/12/22  21:08
 */
@Data
@AllArgsConstructor
public class MetaVo {
    /**
     * 设置该路由在侧边栏和面包屑中展示的名字
     */
    private String title;

    /**
     * 设置该路由的图标，对应路径src/assets/icons/svg
     */
    private String icon;

    /**
     * 设置为true，则不会被 <keep-alive>缓存
     */
    private boolean noCache;


    private Set<String> roles;
}
