package com.zh.oes.sms.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/*
 * sms的配置类,用于读取application.properties
 * 继承InitializingBean,则该类将自动读取配置文件中的属性
 */
@Component //该注解会将该类交给spring处理
public class ConstantPropertiesUtil implements InitializingBean {
    // @Value注解将读取配置文件内容,但不能注入static字段
    @Value("${tencent.msm.secret_id}")
    private String secretId;

    @Value("${tencent.msm.secret_key}")
    private String secretKey;

    @Value("${tencent.msm.end_point}")
    private String endPoint;

    @Value("${tencent.msm.app_id}")
    private String appId;

    @Value("${tencent.msm.sign_name}")
    private String signName;

    @Value("${tencent.msm.template_id}")
    private String templateId;

    public static String SECRET_ID;
    public static String SECRET_KEY;
    public static String END_POINT;
    public static String APP_ID;
    public static String SIGN_NAME;
    public static String TEMPLATE_ID;

    // 在所有属性初始化后将执行
    @Override
    public void afterPropertiesSet() {
        SECRET_ID = secretId;
        SECRET_KEY = secretKey;
        END_POINT = endPoint;
        APP_ID = appId;
        SIGN_NAME = signName;
        TEMPLATE_ID = templateId;
        System.out.println("sign name is " + SIGN_NAME);
    }
}

