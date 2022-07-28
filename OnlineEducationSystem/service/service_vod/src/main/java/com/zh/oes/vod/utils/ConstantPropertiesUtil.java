package com.zh.oes.vod.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * vod的配置类,用于读取application.properties
 * 继承InitializingBean,则该类将自动读取配置文件中的属性
 */
@Component
public class ConstantPropertiesUtil implements InitializingBean {
    @Value("${aliyun.vod.file.key_id}")
    private String keyId;

    @Value("${aliyun.vod.file.key_secret}")
    private String keySecret;

    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;

    // 在所有属性初始化后将执行
    @Override
    public void afterPropertiesSet() {
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
    }
}
