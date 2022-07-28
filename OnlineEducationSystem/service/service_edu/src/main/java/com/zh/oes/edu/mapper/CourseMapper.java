package com.zh.oes.edu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zh.oes.model.entity.edu.Course;
import com.zh.oes.model.vo.edu.admin.CoursePublishVO;
import com.zh.oes.model.vo.edu.user.CourseUserInfoVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-21
 */
@Mapper
public interface CourseMapper extends BaseMapper<Course> {
    // 查询课程发布需要显示的内容
    CoursePublishVO getPublishCourse(Long courseId);

    // 学员端根据课程id查询课程信息,包括课程下的章节和小节
    CourseUserInfoVO getUserCourseInfo(Long courseId);
}
