package com.zh.oes.edu.controller.user;


import com.zh.oes.common.utils.JwtUtil;
import com.zh.oes.common.utils.Result;
import com.zh.oes.edu.service.StudyRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 学生学习记录 前端控制器
 * </p>
 *
 * @author nichijoux
 * @since 2022-08-14
 */
@Api(tags = "学习记录用户端api")
@RestController
@RequestMapping("user/edu/studyRecord")
public class StudyRecordUserController {
    private StudyRecordService studyRecordService;

    @Autowired
    public void setStudyRecordService(StudyRecordService studyRecordService) {
        this.studyRecordService = studyRecordService;
    }

    @ApiOperation(value = "添加学习记录")
    @PostMapping("addStudyRecord/{teacherName}/{courseName}/{videoName}/{videoSourceId}/{courseId}")
    public Result addStudyRecord(
            @ApiParam(name = "teacherName", value = "讲师名", required = true)
            @PathVariable String teacherName,
            @ApiParam(name = "courseName", value = "课程名", required = true)
            @PathVariable String courseName,
            @ApiParam(name = "courseCover", value = "课程封面", required = true)
            @RequestParam("courseCover") String courseCover,
            @ApiParam(name = "videoName", value = "小节名", required = true)
            @PathVariable String videoName,
            @ApiParam(name = "videoSourceId", value = "阿里云视频id", required = true)
            @PathVariable String videoSourceId,
            @ApiParam(name = "courseId", value = "课程id", required = true)
            @PathVariable Long courseId,
            @ApiParam(name = "request", value = "获取用户的id")
                    HttpServletRequest request) {
        String userId = JwtUtil.getUserIdByJwtToken(request);
        studyRecordService.addStudyRecord(teacherName, courseName, courseCover, videoName, videoSourceId, courseId, Long.valueOf(userId));
        return Result.success();
    }

    @ApiOperation(value = "分页查询用户的学习记录")
    @PostMapping("pageQueryUserStudyRecord/{index}/{limit}")
    public Result pageQueryUserStudyRecord(
            @ApiParam(name = "index", value = "当前页", required = true)
            @PathVariable Long index,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "request", value = "获取用户的id")
                    HttpServletRequest request) {
        String userId = JwtUtil.getUserIdByJwtToken(request);
        if (!StringUtils.hasText(userId)) {
            return Result.failure().message("请登录账号");
        }
        Map<String, Object> data = studyRecordService.pageQueryUserStudyRecord(index, limit, Long.valueOf(userId));
        return Result.success(data);
    }
}

