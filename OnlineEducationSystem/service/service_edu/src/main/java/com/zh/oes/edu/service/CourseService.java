package com.zh.oes.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.oes.model.entity.edu.Course;
import com.zh.oes.model.vo.edu.admin.CourseInfoVO;
import com.zh.oes.model.vo.edu.admin.CourseListVO;
import com.zh.oes.model.vo.edu.admin.CoursePublishVO;
import com.zh.oes.model.vo.edu.admin.CourseQueryCondition;
import com.zh.oes.model.vo.edu.user.CourseUserInfoVO;
import com.zh.oes.model.vo.edu.user.CourseUserQueryCondition;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-21
 */
public interface CourseService extends IService<Course> {
    // 获取所有课程信息
    List<CourseListVO> getAllCourse();

    // 分页查询获取课程信息
    Map<String, Object> pageQueryCourse(Long index, Long limit, CourseQueryCondition queryCondition);

    // 用户端分页查询获取课程信息
    Map<String, Object> pageQueryCourse(Long index, Long limit, CourseUserQueryCondition queryCondition);

    // 根据课程id获取课程基本信息
    CourseInfoVO getCourseInfo(Long courseId);

    // 添加课程基本信息
    Long addCourse(CourseInfoVO courseInfo);

    // 根据课程id更新课程基本信息
    boolean updateCourse(CourseInfoVO courseInfo);

    // 根据课程id发布课程
    boolean publishCourse(Long courseId);

    // 获取课程发布时要显示的数据
    CoursePublishVO getPublishCourse(Long courseId);

    // 根据课程id删除课程
    boolean deleteCourse(Long courseId);

    // 获取热门课程
    List<Course> getHotCourseList();

    // 根据讲师id获取其所讲课程
    List<Course> getCourseByTeacherId(Long teacherId);

    // 获取用户端课程信息
    Map<String, Object> getUserCourseInfo(Long courseId, HttpServletRequest request);

    // 根据课程id获取课程基本信息
    CourseUserInfoVO getUserCourseInfoById(Long courseId);
}
