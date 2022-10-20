package com.zh.oes.common.base.client;

import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "service-order",fallback = OrderClientHystrix.class)
public interface OrderClient {
    @GetMapping("user/order/payOrder/remoteGetUserHaveBuyCourse/{courseId}/{memberId}")
    Boolean remoteGetUserHaveBuyCourse(
            @ApiParam(name = "courseId", value = "课程id", required = true)
            @PathVariable("courseId") Long courseId,
            @ApiParam(name = "memberId", value = "用户id", required = true)
            @PathVariable("memberId") Long memberId);

    @GetMapping("admin/order/payOrder/remoteCountOrder")
    Long remoteCountOrder();
}
