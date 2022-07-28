package com.zh.oes.common.base.client;

import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * service_vod服务的远程接口
 */
@Component
@FeignClient(name = "service-vod",fallback = VodClientHystrix.class)
// name对应的是application.properties中的spring.application.name,fallback是服务宕机时调用的服务类
public interface VodClient {
    // 远程调用的微服务方法接口地址
    @DeleteMapping("admin/vod/remoteDeleteAliVideo/{videoSourceId}")
    boolean remoteDeleteAliVideo(
            @ApiParam(name = "videoSourceId", value = "视频id", required = true)
            @PathVariable("videoSourceId") String videoSourceId);

    // 远程调用service_vod中批量删除云端视频的接口方法
    @DeleteMapping("admin/vod/remoteBatchDeleteAliVideo")
    boolean remoteBatchDeleteAliVideo(
            @ApiParam(name = "videoSourceIdList", value = "小节视频播放id列表", required = true)
            @RequestParam("videoSourceIdList") List<String> videoSourceIdList);
}