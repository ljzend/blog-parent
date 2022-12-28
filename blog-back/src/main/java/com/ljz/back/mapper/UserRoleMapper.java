package com.ljz.back.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ljz.back.pojo.UserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户和角色关联表 Mapper 接口
 * </p>
 *
 * @author ljz
 * @since 2022-12-22 09-43-46
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

}
