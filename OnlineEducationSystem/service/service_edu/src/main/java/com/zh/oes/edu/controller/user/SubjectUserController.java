package com.zh.oes.edu.controller.user;

import com.zh.oes.common.utils.Result;
import com.zh.oes.edu.service.SubjectService;
import com.zh.oes.model.entity.edu.LevelSubject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "学科前台api接口")
@RestController
@RequestMapping("user/edu/subject")
public class SubjectUserController {
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
}
