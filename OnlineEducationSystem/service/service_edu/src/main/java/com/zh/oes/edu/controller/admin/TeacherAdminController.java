package com.zh.oes.edu.controller.admin;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.oes.common.utils.Result;
import com.zh.oes.edu.service.TeacherService;
import com.zh.oes.model.entity.edu.Teacher;
import com.zh.oes.model.vo.edu.admin.TeacherQueryCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师后台 前端控制器
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-21
 */
@Api(tags = "讲师后台api接口")
@RestController
@RequestMapping("/admin/edu/teacher")
public class TeacherAdminController {
    private TeacherService teacherService;

    @Autowired
    public void setTeacherService(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @ApiOperation(value = "获取全部讲师")
    @GetMapping("getAllTeacher")
    public Result getAllTeacher() {
        List<Teacher> teacherList = teacherService.list(null);
        return Result.success(teacherList);
    }

    @ApiOperation(value = "根据讲师id获取讲师")
    @GetMapping("getTeacher/{teacherId}")
    public Result getTeacher(
            @ApiParam(name = "teacherId", value = "要查询的讲师id", required = true)
            @PathVariable Long teacherId) {
        Teacher teacher = teacherService.getById(teacherId);
        return Result.success(teacher);
    }

    @ApiOperation(value = "多条件分页查询讲师信息")
    @PostMapping("pageQueryTeacher/{index}/{limit}")
    public Result pageQueryTeacher(
            @ApiParam(name = "index", value = "当前页", required = true)
            @PathVariable Long index,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "queryCondition", value = "查询条件")
            @Validated @RequestBody(required = false) TeacherQueryCondition queryCondition) {
        Page<Teacher> teacherList = teacherService.pageQueryTeacher(index, limit, queryCondition);
        return Result.success(teacherList);
    }

    @ApiOperation(value = "添加讲师信息")
    @PostMapping("addTeacher")
    public Result addTeacher(
            @ApiParam(name = "teacher", value = "要添加的讲师的信息", required = true)
            @Validated @RequestBody Teacher teacher) {
        return teacherService.save(teacher) ?
                Result.success() : Result.failure().message("添加讲师失败");
    }

    @ApiOperation(value = "更新讲师信息")
    @PostMapping("updateTeacher")
    public Result updateTeacher(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @Validated @RequestBody Teacher teacher) {
        return teacherService.updateById(teacher) ?
                Result.success() : Result.failure().message("更新讲师信息失败");
    }

    @ApiOperation(value = "根据id删除讲师")
    @DeleteMapping("deleteTeacher/{teacherId}")
    public Result deleteTeacher(
            @ApiParam(name = "teacherId", value = "要删除的讲师的id", required = true)
            @PathVariable Long teacherId) {
        return teacherService.removeById(teacherId) ?
                Result.success() : Result.failure().message("删除讲师失败");
    }
}