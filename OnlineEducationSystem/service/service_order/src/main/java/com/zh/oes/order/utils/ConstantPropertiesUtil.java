package com.zh.oes.order.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component //该注解会将该类交给spring处理
public class ConstantPropertiesUtil implements InitializingBean {
    // @Value注解将读取配置文件内容,但不能注入static字段
    @Value("${wx.pay.app_id}")
    private String appId;

    @Value("${wx.pay.partner}")
    private String partner;

    @Value("${wx.pay.partner_key}")
    private String partnerKey;

    @Value("${wx.pay.notify_url}")
    private String notifyUrl;

    public static String WX_PAY_APP_ID;
    public static String WX_PAY_PARTNER;
    public static String WX_PAY_PARTNER_KEY;
    public static String WX_PAY_NOTIFY_URL;

    // 在所有属性初始化后将执行
    @Override
    public void afterPropertiesSet() {
        WX_PAY_APP_ID = appId;
        WX_PAY_PARTNER = partner;
        WX_PAY_PARTNER_KEY = partnerKey;
        WX_PAY_NOTIFY_URL = notifyUrl;
    }
}
