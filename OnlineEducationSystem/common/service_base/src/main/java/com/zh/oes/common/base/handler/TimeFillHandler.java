package com.zh.oes.common.base.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

// 自动填充时间数据
@Component
public class TimeFillHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        // fieldName为属性名
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // fieldName为属性名
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }
}