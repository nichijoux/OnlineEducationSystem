package com.zh.oes.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/*
 * oss的配置类,用于读取application.properties
 * 继承InitializingBean,则该类将自动读取配置文件中的属性
 */
@Component //该注解会将该类交给spring处理
public class ConstantPropertiesUtil implements InitializingBean {
    // @Value注解将读取配置文件内容,但不能注入static字段
    @Value("${aliyun.oss.file.end_point}")
    private String endPoint;

    @Value("${aliyun.oss.file.key_id}")
    private String keyId;

    @Value("${aliyun.oss.file.key_secret}")
    private String keySecret;

    @Value("${aliyun.oss.file.bucket_name}")
    private String bucketName;

    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;

    // 在所有属性初始化后将执行
    @Override
    public void afterPropertiesSet() {
        END_POINT = endPoint;
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
        BUCKET_NAME = bucketName;
    }
}