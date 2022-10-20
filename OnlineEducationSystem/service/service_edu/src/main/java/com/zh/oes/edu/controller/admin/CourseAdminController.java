package com.zh.oes.edu.controller.admin;


import com.zh.oes.common.utils.Result;
import com.zh.oes.edu.service.CourseService;
import com.zh.oes.model.entity.edu.Course;
import com.zh.oes.model.vo.edu.admin.CourseInfoVO;
import com.zh.oes.model.vo.edu.admin.CoursePublishVO;
import com.zh.oes.model.vo.edu.admin.CourseQueryCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-21
 */
@Api(tags = "课程后台api接口")
@RestController
@RequestMapping("admin/edu/course")
public class CourseAdminController {
    private CourseService courseService;

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @ApiOperation(value = "获取所有课程")
    @GetMapping("getAllCourse")
    public Result getAllCourse() {
        List<Course> coursesList = courseService.getAllCourse();
        return Result.success(coursesList);
    }

    @ApiOperation(value = "分页查询课程")
    @PostMapping("pageQueryCourse/{index}/{limit}")
    public Result pageQueryCourse(
            @ApiParam(name = "index", value = "当前页", required = true)
            @PathVariable Long index,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "queryCondition", value = "查询条件,应该为json数据")
            @RequestBody(required = false) CourseQueryCondition queryCondition) {
        Map<String, Object> data = courseService.pageQueryCourse(index, limit, queryCondition);
        return Result.success(data);
    }

    @ApiOperation(value = "根据课程id获取课程基本信息")
    @GetMapping("getCourse/{courseId}")
    public Result getCourse(
            @ApiParam(name = "courseId", value = "课程id", required = true)
            @PathVariable Long courseId) {
        CourseInfoVO courseInfo = courseService.getCourseInfo(courseId);
        return courseInfo != null ?
                Result.success(courseInfo) : Result.failure().message("获取课程基本信息失败");
    }

    @ApiOperation(value = "添加课程基本信息")
    @PostMapping("addCourse")
    public Result addCourse(
            @ApiParam(name = "courseInfo", value = "课程基本信息实体", required = true)
            @Validated @RequestBody CourseInfoVO courseInfo) {
        Long courseId = courseService.addCourse(courseInfo);
        return Result.success(courseId);
    }

    @ApiOperation(value = "根据课程id更新课程基本信息")
    @PostMapping("updateCourse")
    public Result updateCourse(
            @ApiParam(name = "courseInfo", value = "课程信息", required = true)
            @Validated @RequestBody CourseInfoVO courseInfo) {
        return courseService.updateCourse(courseInfo) ?
                Result.success() : Result.failure().message("更新课程信息失败");
    }

    @ApiOperation(value = "根据课程id获取课程发布时要显示的信息")
    @GetMapping("getPublishCourse/{courseId}")
    public Result getPublishCourse(
            @ApiParam(name = "courseId", value = "要获取信息的课程id", required = true)
            @PathVariable Long courseId) {
        CoursePublishVO coursePublishVO = courseService.getPublishCourse(courseId);
        return Result.success(coursePublishVO);
    }

    @ApiOperation(value = "根据课程id发布课程")
    @PostMapping("publishCourse/{courseId}")
    public Result publishCourse(
            @ApiParam(name = "courseId", value = "课程id", required = true)
            @PathVariable Long courseId) {
        return courseService.publishCourse(courseId) ?
                Result.success() : Result.failure().message("发布课程失败");
    }

    @ApiOperation(value = "根据课程id删除课程信息,包括其下的小节和章节")
    @DeleteMapping("deleteCourse/{courseId}")
    public Result deleteCourse(
            @ApiParam(name = "courseId", value = "要删除的课程id", required = true)
            @PathVariable Long courseId) {
        return courseService.deleteCourse(courseId) ?
                Result.success() : Result.failure().message("删除课程失败");
    }

    @ApiOperation(value = "远程调用,统计课程数量")
    @GetMapping("remoteCountCourse")
    public Long remoteCountCourse() {
        return courseService.count();
    }
}

