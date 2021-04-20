package com.th.workbase.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Date 2021-04-17-21:05
 * @Author tangJ
 * @Description
 * @Version 1.0
 */
@Component
public class MetaDataConfig implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("CreateTime", new Date(), metaObject);
        this.setFieldValByName("UpdateTime", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("UpdateTime", new Date(), metaObject);
    }
}
