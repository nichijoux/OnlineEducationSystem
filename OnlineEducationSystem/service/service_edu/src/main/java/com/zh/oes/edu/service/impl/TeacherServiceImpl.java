package com.zh.oes.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.oes.edu.mapper.TeacherMapper;
import com.zh.oes.edu.service.TeacherService;
import com.zh.oes.model.entity.edu.Teacher;
import com.zh.oes.model.vo.edu.admin.TeacherQueryCondition;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-21
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
    /**
     * 分页条件查询讲师信息
     *
     * @param index          当前页
     * @param limit          每页记录数
     * @param queryCondition 查询条件
     * @return 分页查询后的对象
     */
    @Override
    public Page<Teacher> pageQueryTeacher(Long index, Long limit, TeacherQueryCondition queryCondition) {
        Page<Teacher> teacherPage = new Page<>(index, limit);
        if (queryCondition == null) {
            return baseMapper.selectPage(teacherPage, null);
        }
        LambdaQueryWrapper<Teacher> wrapper = new LambdaQueryWrapper<>();
        // 获取查询条件
        String name = queryCondition.getName();
        Integer level = queryCondition.getLevel();
        String beginTime = queryCondition.getBeginTime();
        String endTime = queryCondition.getEndTime();
        // 设定sql查询条件
        wrapper.like(!StringUtils.isEmpty(name), Teacher::getName, name);
        wrapper.eq(!StringUtils.isEmpty(level), Teacher::getLevel, level);
        wrapper.ge(!StringUtils.isEmpty(beginTime), Teacher::getCreateTime, beginTime);
        wrapper.le(!StringUtils.isEmpty(endTime), Teacher::getCreateTime, endTime);

        // 排序(目前按创建排序)
        wrapper.orderByAsc(Teacher::getSort);
        wrapper.orderByDesc(Teacher::getCreateTime);

        return baseMapper.selectPage(teacherPage, wrapper);
    }

    /**
     * 获取热门讲师
     *
     * @return 查询到的4个热门讲师
     */
    @Cacheable(value = "teacher",key = "'getHotTeacherList'")
    @Override
    public List<Teacher> getHotTeacherList() {
        LambdaQueryWrapper<Teacher> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Teacher::getSort);
        wrapper.orderByDesc(Teacher::getCreateTime);
        wrapper.last("limit 4");
        return baseMapper.selectList(wrapper);
    }
}
