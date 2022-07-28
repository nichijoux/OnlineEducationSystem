package com.zh.oes.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.oes.model.entity.edu.Teacher;
import com.zh.oes.model.vo.edu.admin.TeacherQueryCondition;

import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-21
 */
public interface TeacherService extends IService<Teacher> {
    // 分页条件查询讲师
    Page<Teacher> pageQueryTeacher(Long index, Long limit, TeacherQueryCondition queryCondition);

    // 获取热门讲师
    List<Teacher> getHotTeacherList();
}
