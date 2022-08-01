package com.zh.oes.edu.controller.admin;


import com.zh.oes.common.base.exception.OESException;
import com.zh.oes.common.utils.Result;
import com.zh.oes.edu.service.SubjectService;
import com.zh.oes.model.entity.edu.LevelSubject;
import com.zh.oes.model.entity.edu.Subject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-23
 */
@Api(tags = "科目后台api接口")
@RestController
@RequestMapping("admin/edu/subject")
public class SubjectAdminController {
    private SubjectService subjectService;

    @Autowired
    public void setSubjectService(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @ApiOperation(value = "查询所有分类,一级分类下面包含二级分类,二级还可以包含三级")
    @GetMapping("getAllSubject")
    public Result getAllSubject() {
        List<LevelSubject> subjectList = subjectService.getAllSubject();
        return Result.success(subjectList);
    }

    @ApiOperation(value = "获取学科pid为{pid}的科目及其子节点")
    @GetMapping("getChildSubject/{pid}")
    public Result getChildSubject(
            @ApiParam(name = "id", value = "要查询的节点id", required = true)
            @PathVariable Long pid) {
        List<LevelSubject> subjectList = subjectService.getChildSubject(pid);
        return Result.success(subjectList);
    }

    @ApiOperation(value = "为subjectId下添加子节点")
    @PostMapping("addChildSubject/{id}")
    public Result addChildSubject(
            @ApiParam(name = "id", value = "要添加的父节点id", required = true)
            @PathVariable Long id,
            @ApiParam(name = "subject", value = "要添加的学科实体", required = true)
            @Validated @RequestBody Subject subject) {
        // 先判断父节点下的一层的子节点是否有重复的
        subject.setPid(id);
        return subjectService.addChildSubject(subject) ?
                Result.success() : Result.failure().message("添加子科目失败");
    }

    @ApiOperation(value = "将id为{subjectId}的科目更新")
    @PostMapping("updateSubject")
    public Result updateSubject(
            @ApiParam(name = "subject", value = "更新应该有的值", required = true)
            @RequestBody LevelSubject levelSubject) {
        Subject subject = subjectService.getById(levelSubject.getId());
        if (subject == null) {
            throw new OESException("学科不存在");
        }
        subject.setTitle(levelSubject.getTitle());
        return subjectService.updateById(subject) ?
                Result.success() : Result.failure().message("更新科目信息失败");
    }

    @ApiOperation(value = "根据学科id删除科目分类及其子节点")
    @DeleteMapping("deleteSubject/{subjectId}")
    public Result deleteSubject(
            @ApiParam(name = "subjectId", value = "要删除的学科id", required = true)
            @PathVariable Long subjectId) {
        return subjectService.deleteSubject(subjectId) ?
                Result.success() : Result.failure().message("递归删除科目分类失败");
    }

    @ApiOperation(value = "下载科目为excel文件")
    @GetMapping("downloadSubject")
    public void downloadSubject(HttpServletResponse response) {
        subjectService.downloadSubject(response);
    }
}

