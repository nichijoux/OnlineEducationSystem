package com.zh.oes.edu.controller.user;

import com.zh.oes.common.utils.Result;
import com.zh.oes.edu.service.CourseService;
import com.zh.oes.edu.service.TeacherService;
import com.zh.oes.model.entity.edu.Course;
import com.zh.oes.model.entity.edu.Teacher;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "用户前台首页api接口")
@RestController
@RequestMapping("user/edu/index/")
public class IndexUserController {
    private CourseService courseService;

    private TeacherService teacherService;

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @Autowired
    public void setTeacherService(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @ApiOperation(value = "查询前8条热门课程,前4个热门讲师")
    @GetMapping("getHotTeacherAndHotCourse")
    public Result getHotTeacherAndHotCourse() {
        // 按id降序排列,取出前4个课程
        List<Teacher> teacherList = teacherService.getHotTeacherList();
        // 按照id降序排列,取出前8个课程
        List<Course> courseList = courseService.getHotCourseList();
        Map<String,Object> data = new HashMap<>();
        data.put("hotTeacher",teacherList);
        data.put("hotCourse",courseList);
        // 返回数据
        return Result.success(data);
    }
}
