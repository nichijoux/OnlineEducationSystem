package com.zh.oes.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.oes.model.entity.edu.StudyRecord;
import com.zh.oes.model.vo.edu.admin.StudyRecordQueryCondition;

import java.util.Map;

/**
 * <p>
 * 学生学习记录 服务类
 * </p>
 *
 * @author nichijoux
 * @since 2022-08-14
 */
public interface StudyRecordService extends IService<StudyRecord> {
    // 添加学习记录
    void addStudyRecord(String teacherName, String courseName, String courseCover, String videoName, String videoSourceId, Long courseId, Long userId);

    // 分页查询用户学习记录
    Map<String, Object> pageQueryUserStudyRecord(Long index, Long limit, Long userId);

    // 管理员分页查询学习记录
    Page<StudyRecord> pageQueryStudyRecord(Long index, Long limit, StudyRecordQueryCondition queryCondition);
}
