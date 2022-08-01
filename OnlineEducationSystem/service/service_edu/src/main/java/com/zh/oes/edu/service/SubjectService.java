package com.zh.oes.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.oes.model.entity.edu.LevelSubject;
import com.zh.oes.model.entity.edu.Subject;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-23
 */
public interface SubjectService extends IService<Subject> {
    // 获取所有的科目
    List<LevelSubject> getAllSubject();

    // 获取学科pid为{pid}的节点及其子节点
    List<LevelSubject> getChildSubject(Long pid);

    // 在subject的父节点下添加一个新的科目
    boolean addChildSubject(Subject subject);

    // 根据学科id删除学科分类及其子节点
    boolean deleteSubject(Long subjectId);

    // 下载科目为excel文件
    void downloadSubject(HttpServletResponse response);
}
