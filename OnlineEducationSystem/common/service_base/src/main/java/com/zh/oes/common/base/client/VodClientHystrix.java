package com.zh.oes.common.base.client;

import com.zh.oes.common.base.exception.OESException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VodClientHystrix implements VodClient {
    @Override
    public boolean remoteDeleteAliVideo(String videoSourceId) {
        throw new OESException("连接VOD服务器失败");
    }

    @Override
    public boolean remoteBatchDeleteAliVideo(List<String> videoSourceIdList) {
        throw new OESException("连接VOD服务器失败");
    }
}
