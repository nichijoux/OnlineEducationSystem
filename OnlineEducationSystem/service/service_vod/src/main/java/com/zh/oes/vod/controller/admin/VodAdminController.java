package com.zh.oes.vod.controller.admin;

import com.zh.oes.common.utils.Result;
import com.zh.oes.vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api(tags = "视频点播后台api接口")
@RestController
@RequestMapping("admin/vod/")
public class VodAdminController {
    private VodService vodService;

    @Autowired
    public void setVodService(VodService vodService) {
        this.vodService = vodService;
    }

    @ApiOperation(value = "上传视频到云端")
    @PostMapping("uploadAliVideo")
    public Result uploadAliVideo(
            @ApiParam(name = "file", value = "要上传的视频文件", required = true)
            @RequestBody MultipartFile file) {
        String videoId = vodService.uploadAliVideo(file);
        return Result.success(videoId);
    }

    @ApiOperation(value = "根据视频id删除阿里云端视频")
    @DeleteMapping("deleteAliVideo/{videoSourceId}")
    public Result deleteAliVideo(
            @ApiParam(name = "videoId", value = "视频id", required = true)
            @PathVariable("videoSourceId") String videoSourceId) {
        vodService.deleteAliVideo(videoSourceId);
        return Result.success();
    }

    @ApiOperation(value = "远程调用,根据视频id删除阿里云端视频")
    @DeleteMapping("remoteDeleteAliVideo/{videoSourceId}")
    public boolean remoteDeleteAliVideo(
            @ApiParam(name = "videoSourceId", value = "视频id", required = true)
            @PathVariable("videoSourceId") String videoSourceId) {
        vodService.deleteAliVideo(videoSourceId);
        return true;
    }

    @ApiOperation(value = "远程调用,根据视频id列表批量删除阿里云端视频")
    @DeleteMapping("remoteBatchDeleteAliVideo")
    public boolean remoteBatchDeleteAliVideo(
            @ApiParam(name = "videoSourceIdList", value = "小节视频播放id列表", required = true)
            @RequestParam("videoSourceIdList") List<String> videoSourceIdList) {
        vodService.batchDeleteAliVideo(videoSourceIdList);
        return true;
    }
}
