package com.zh.oes.sms.controller;

import com.zh.oes.common.utils.RandomUtil;
import com.zh.oes.common.utils.Result;
import com.zh.oes.sms.service.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@Api(tags = "短信服务API")
@RestController
@RequestMapping("user/sms")
public class SmsController {
    // 短信服务
    private SmsService smsService;

    // redis服务
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    public void setSmsService(SmsService smsService) {
        this.smsService = smsService;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @ApiOperation(value = "根据手机号获取验证码")
    @GetMapping("/getVerificationCode/{phone}")
    public Result getVerificationCode(
            @ApiParam(name = "phone", value = "手机号", required = true)
            @PathVariable String phone) {
        if (phone == null) {
            return Result.failure().message("手机号码为空");
        }

        //先去redis中查看短信是否已经发送过了
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)) {
            return Result.success();
        }

        // 没发送过,生成六位验证码
        code = RandomUtil.getSixBitRandom();
        // 过期时间
        String expireTime = "5";
        // param中code表示验证码,expire_at代表过期时间
        String[] params = {code, expireTime};
        // 阿里云短信服务难申请,因此直接在sendVerificationCode函数中返回为true
        // 现在改为腾讯云短信服务
        // 腾讯云需要将手机号加上86
        boolean isSend = smsService.getVerificationCode("86" + phone, params);

        if (isSend) {
            //如果发送短信成功，存入redis缓存
            redisTemplate.opsForValue().set(phone, code, Integer.parseInt(expireTime), TimeUnit.MINUTES);
            return Result.success();
        } else {
            return Result.failure().message("发送短信失败");
        }
    }
}
