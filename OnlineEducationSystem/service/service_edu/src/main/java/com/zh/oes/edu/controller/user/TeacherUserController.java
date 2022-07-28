package com.zh.oes.edu.controller.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.oes.common.utils.Result;
import com.zh.oes.edu.service.CourseService;
import com.zh.oes.edu.service.TeacherService;
import com.zh.oes.model.entity.edu.Course;
import com.zh.oes.model.entity.edu.Teacher;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "讲师前台api接口")
@RestController
@RequestMapping("/user/edu/teacher")
public class TeacherUserController {
    private TeacherService teacherService;

    private CourseService courseService;

    @Autowired
    public void setTeacherService(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @ApiOperation(value = "分页查询讲师信息")
    @GetMapping("pageQueryTeacher/{index}/{limit}")
    public Result pageQueryTeacher(
            @ApiParam(name = "index", value = "当前页", required = true)
            @PathVariable Long index,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit) {
        Page<Teacher> teacherList = teacherService.pageQueryTeacher(index, limit, null);
        Map<String,Object> data = new HashMap<>();
        data.put("total", teacherList.getTotal());
        data.put("records", teacherList.getRecords());
        data.put("pages", teacherList.getPages());
        data.put("current", teacherList.getCurrent());
        data.put("size", teacherList.getSize());
        data.put("hasNext", teacherList.hasNext());
        data.put("hasPrevious", teacherList.hasPrevious());
        return Result.success(data);
    }

    @ApiOperation(value = "根据讲师id获取讲师,还需要包括讲师所讲课程")
    @GetMapping("getTeacherAndCourse/{teacherId}")
    public Result getTeacherAndCourse(
            @ApiParam(name = "teacherId", value = "要查询的讲师id", required = true)
            @PathVariable Long teacherId) {
        // 获取讲师信息
        Teacher teacher = teacherService.getById(teacherId);
        // 获取讲师课程
        List<Course> courseList = courseService.getCourseByTeacherId(teacherId);
        Map<String, Object> data = new HashMap<>();
        data.put("teacher", teacher);
        data.put("courseList", courseList);
        return Result.success(data);
    }
}
