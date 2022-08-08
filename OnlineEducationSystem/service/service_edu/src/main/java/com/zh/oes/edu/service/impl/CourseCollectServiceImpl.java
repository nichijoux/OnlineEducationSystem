package com.zh.oes.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zh.oes.model.entity.edu.CourseCollect;
import com.zh.oes.edu.mapper.CourseCollectMapper;
import com.zh.oes.edu.service.CourseCollectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程收藏 服务实现类
 * </p>
 *
 * @author nichijoux
 * @since 2022-08-08
 */
@Service
public class CourseCollectServiceImpl extends ServiceImpl<CourseCollectMapper, CourseCollect> implements CourseCollectService {

    /**
     * 根据用户id判断是否收藏了课程
     *
     * @param memberId 用户id
     * @return 是否收藏了课程
     */
    @Override
    public boolean haveCollectCourse(Long memberId,Long courseId) {
        LambdaQueryWrapper<CourseCollect> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CourseCollect::getMemberId, memberId);
        wrapper.eq(CourseCollect::getCourseId, courseId);
        return baseMapper.exists(wrapper);
    }
}
