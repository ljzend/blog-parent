package com.ljz.back.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ljz.back.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * <p> Mybatis-Plus 自动填充配置类</p>
 *
 * @Author : ljz
 * @Date: 2022/12/12  10:04
 */

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * <p> 插入数据时填充 create_time 和 update_time 字段 </p>
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("mybatis-plus插入自动填充");
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "createBy", String.class, SecurityUtils.getLoginUser().getUsername());
        this.strictInsertFill(metaObject, "updateBy", String.class, SecurityUtils.getLoginUser().getUsername());
    }

    /**
     * <p> 插入数据时更新 update_time 字段 </p>
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("mybatis-plus更新自动填充");
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
        this.strictUpdateFill(metaObject, "updateBy", String.class, SecurityUtils.getLoginUser().getUsername());
    }
}
