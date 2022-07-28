package com.zh.oes.vod.controller.user;

import com.zh.oes.common.utils.Result;
import com.zh.oes.vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "视频点播后台api接口")
@RestController
@RequestMapping("user/vod")
public class VodUserController {
    private VodService vodService;

    @Autowired
    public void setVodService(VodService vodService) {
        this.vodService = vodService;
    }

    @ApiOperation(value = "根据视频id获取视频播放凭证")
    @GetMapping("getPlayAuth/{videoId}")
    public Result getPlayAuth(
            @ApiParam(name = "videoId", value = "视频id", required = true)
            @PathVariable String videoId) {
        String playAuth = vodService.getPlayAuth(videoId);
        return Result.success(playAuth);
    }
}
