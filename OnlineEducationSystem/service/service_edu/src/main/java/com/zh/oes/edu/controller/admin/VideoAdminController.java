package com.zh.oes.edu.controller.admin;


import com.zh.oes.common.utils.Result;
import com.zh.oes.edu.service.VideoService;
import com.zh.oes.model.entity.edu.Video;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-23
 */
@Api(tags = "小节后台api接口")
@RestController
@RequestMapping("admin/edu/video")
public class VideoAdminController {
    private VideoService videoService;

    @Autowired
    public void setVideoService(VideoService videoService) {
        this.videoService = videoService;
    }

    @ApiOperation(value = "根据小节id获取小节信息")
    @GetMapping("getVideo/{videoId}")
    public Result getVideo(
            @ApiParam(name = "videoId", value = "要查询的小节id", required = true)
            @PathVariable Long videoId) {
        Video video = videoService.getById(videoId);
        return video != null ?
                Result.success(video) : Result.failure().message("小节不存在");
    }


    @ApiOperation(value = "添加小节")
    @PostMapping("addVideo")
    public Result addVideo(
            @ApiParam(name = "video", value = "要添加的小节视频", required = true)
            @Validated @RequestBody Video video) {
        return videoService.save(video) ?
                Result.success() : Result.failure().message("添加小节失败");
    }

    @ApiOperation(value = "修改小节")
    @PostMapping("updateVideo")
    public Result updateVideo(
            @ApiParam(name = "video", value = "要修改的视频信息", required = true)
            @Validated @RequestBody Video video) {
        return videoService.updateVideo(video) ?
                Result.success() : Result.failure().message("修改小节信息失败");
    }

    @ApiOperation(value = "根据id删除小节视频,底层会调用vod删除阿里云端的视频")
    @DeleteMapping("deleteVideo/{videoId}")
    public Result deleteVideo(
            @ApiParam(name = "videoId", value = "要删除的视频的id", required = true)
            @PathVariable Long videoId) {
        return videoService.deleteVideo(videoId) ?
                Result.success() : Result.failure().message("删除小节失败");
    }
}

