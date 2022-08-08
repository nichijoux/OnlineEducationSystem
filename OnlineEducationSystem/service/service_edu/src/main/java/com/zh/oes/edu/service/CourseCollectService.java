package com.zh.oes.edu.service;

import com.zh.oes.model.entity.edu.CourseCollect;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程收藏 服务类
 * </p>
 *
 * @author nichijoux
 * @since 2022-08-08
 */
public interface CourseCollectService extends IService<CourseCollect> {

    boolean haveCollectCourse(Long memberId,Long courseId);
}
