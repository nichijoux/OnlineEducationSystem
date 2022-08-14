package com.zh.oes.edu.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.oes.common.utils.Result;
import com.zh.oes.edu.service.StudyRecordService;
import com.zh.oes.model.entity.edu.StudyRecord;
import com.zh.oes.model.vo.edu.admin.StudyRecordQueryCondition;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/edu/studyRecord")
public class StudyRecordAdminController {
    private StudyRecordService studyRecordService;

    @Autowired
    public void setStudyRecordService(StudyRecordService studyRecordService) {
        this.studyRecordService = studyRecordService;
    }

    @ApiOperation(value = "分页查询学习记录")
    @PostMapping("pageQueryStudyRecord/{index}/{limit}")
    public Result pageQueryStudyRecord(
            @ApiParam(name = "index", value = "当前页", required = true)
            @PathVariable Long index,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "queryCondition", value = "查询条件")
            @Validated @RequestBody(required = false) StudyRecordQueryCondition queryCondition) {
        Page<StudyRecord> recordList = studyRecordService.pageQueryStudyRecord(index, limit, queryCondition);
        return Result.success(recordList);
    }
}
