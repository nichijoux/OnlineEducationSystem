package com.zh.oes.common.base.client;

import com.zh.oes.common.base.exception.OESException;
import org.springframework.stereotype.Component;

@Component
public class OrderClientHystrix implements OrderClient {
    @Override
    public Boolean remoteGetUserHaveBuyCourse(Long courseId, Long memberId) {
        throw new OESException("Order服务器宕机");
    }
}
