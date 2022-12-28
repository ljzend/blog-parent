package com.ljz.back.domain.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * <p>通用分页查询返回结果</p>
 *
 * @Author : ljz
 * @Date: 2022/12/18  20:37
 */
@Data
@Accessors(chain = true)
public class PageVo<T> {
    private List<T> records;
    private Long total;
}
