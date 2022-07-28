package com.zh.oes.ucenter.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantPropertiesUtil implements InitializingBean {
    @Value("${wx.open.app_id}")
    public String app_id;

    @Value("${wx.open.app_secret}")
    public String app_secret;

    @Value("${wx.open.redirect_url}")
    public String redirect_url;

    public static String WX_OPEN_APP_ID;
    public static String WX_OPEN_APP_SECRET;
    public static String WX_OPEN_REDIRECT_URL;

    @Override
    public void afterPropertiesSet() {
        WX_OPEN_APP_ID = app_id;
        WX_OPEN_APP_SECRET = app_secret;
        WX_OPEN_REDIRECT_URL = redirect_url;
    }
}
