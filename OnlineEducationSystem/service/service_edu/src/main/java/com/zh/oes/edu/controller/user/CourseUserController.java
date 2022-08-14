package com.zh.oes.edu.controller.user;

import com.zh.oes.common.utils.JwtUtil;
import com.zh.oes.common.utils.Result;
import com.zh.oes.edu.service.CourseService;
import com.zh.oes.model.vo.edu.user.CourseUserInfoVO;
import com.zh.oes.model.vo.edu.user.CourseUserQueryCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Api(tags = "课程前台api接口")
@RestController
@RequestMapping("user/edu/course")
public class CourseUserController {
    private CourseService courseService;

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @ApiOperation(value = "分页查询课程")
    @PostMapping("pageQueryCourse/{index}/{limit}")
    public Result pageQueryCourse(
            @ApiParam(name = "index", value = "当前页", required = true)
            @PathVariable Long index,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "queryCondition", value = "查询条件,应该为json数据")
            @RequestBody(required = false) CourseUserQueryCondition queryCondition) {
        Map<String, Object> data = courseService.pageQueryCourse(index, limit, queryCondition);
        return Result.success(data);
    }

    @ApiOperation(value = "课程详情:根据路径中课程id获取课程基本信息和课程章节信息,课程所属讲师")
    @GetMapping("getCourseInfo/{courseId}")
    public Result getCourseInfo(
            @ApiParam(name = "courseId", value = "课程id", required = true)
            @PathVariable Long courseId,
            @ApiParam(name = "request", value = "请求体")
                    HttpServletRequest request) {
        Map<String, Object> data = courseService.getUserCourseInfo(courseId, request);
        return Result.success(data);
    }

    @ApiOperation(value = "分页查询已收藏课程")
    @GetMapping("pageQueryCollectCourse/{index}/{limit}")
    public Result pageQueryCollectCourse(
            @ApiParam(name = "index", value = "当前页", required = true)
            @PathVariable Long index,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "request", value = "获取用户id")
                    HttpServletRequest request) {
        if (JwtUtil.getUserIdByJwtToken((request)).equals("")) {
            return Result.failure().message("请登录用户");
        }
        Long userId = Long.valueOf(JwtUtil.getUserIdByJwtToken(request));
        Map<String, Object> data = courseService.pageQueryCollectCourse(userId, index, limit);
        return Result.success(data);
    }

    @ApiOperation(value = "收藏课程")
    @PostMapping("collectCourse/{courseId}")
    public Result collectCourse(
            @ApiParam(name = "courseId", value = "课程id", required = true)
            @PathVariable Long courseId,
            @ApiParam(name = "request", value = "获取用户id")
                    HttpServletRequest request) {
        if (JwtUtil.getUserIdByJwtToken((request)).equals("")) {
            return Result.failure().message("请登录用户");
        }
        Long memberId = Long.valueOf(JwtUtil.getUserIdByJwtToken(request));
        courseService.collectCourse(courseId, memberId);
        return Result.success();
    }

    @ApiOperation(value = "取消收藏课程")
    @PostMapping("cancelCollectCourse/{courseId}")
    public Result cancelCollectCourse(
            @ApiParam(name = "courseId", value = "课程id", required = true)
            @PathVariable Long courseId,
            @ApiParam(name = "request", value = "获取用户id")
                    HttpServletRequest request) {
        if (JwtUtil.getUserIdByJwtToken((request)).equals("")) {
            return Result.failure().message("请登录用户");
        }
        Long memberId = Long.valueOf(JwtUtil.getUserIdByJwtToken(request));
        courseService.cancelCollectCourse(courseId, memberId);
        return Result.success();
    }

    @ApiOperation(value = "远程调用,根据课程id获取课程基本信息")
    @GetMapping("remoteGetCourseInfo/{courseId}")
    public CourseUserInfoVO remoteGetCourseInfo(
            @ApiParam(name = "courseId", value = "课程id", required = true)
            @PathVariable("courseId") Long courseId) {
        return courseService.getUserCourseInfoById(courseId);
    }
}
