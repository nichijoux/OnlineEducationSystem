package com.zh.oes.sms.service;

public interface SmsService {
    // 根据手机号获取验证码
    boolean getVerificationCode(String phone, String[] params);
}
