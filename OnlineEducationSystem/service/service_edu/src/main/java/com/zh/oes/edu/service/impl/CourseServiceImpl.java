package com.zh.oes.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.oes.common.base.exception.OESException;
import com.zh.oes.common.utils.JwtUtil;
import com.zh.oes.common.base.client.OrderClient;
import com.zh.oes.edu.mapper.CourseMapper;
import com.zh.oes.edu.service.ChapterService;
import com.zh.oes.edu.service.CourseCollectService;
import com.zh.oes.edu.service.CourseService;
import com.zh.oes.edu.service.TeacherService;
import com.zh.oes.model.entity.edu.Course;
import com.zh.oes.model.entity.edu.CourseCollect;
import com.zh.oes.model.entity.edu.Teacher;
import com.zh.oes.model.vo.edu.admin.*;
import com.zh.oes.model.vo.edu.user.CourseUserInfoVO;
import com.zh.oes.model.vo.edu.user.CourseUserQueryCondition;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-21
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    private TeacherService teacherService;

    private ChapterService chapterService;

    private CourseCollectService collectService;

    private OrderClient orderClient;

    @Autowired
    public void setTeacherService(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Autowired
    public void setChapterService(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @Autowired
    public void setCollectService(CourseCollectService collectService) {
        this.collectService = collectService;
    }

    @Autowired
    public void setOrderClient(OrderClient orderClient) {
        this.orderClient = orderClient;
    }

    /**
     * 获取所有课程信息
     *
     * @return CourseListVO
     */
    @Override
    public List<CourseListVO> getAllCourse() {
        return null;
    }

    /**
     * 分页查询获取课程信息
     *
     * @param index          当前页
     * @param limit          每页记录数
     * @param queryCondition 查询条件
     * @return 分页查询到的课程数据
     */
    @Override
    public Map<String, Object> pageQueryCourse(Long index, Long limit, CourseQueryCondition queryCondition) {
        // 查询获取分页数据
        Page<Course> coursePage = new Page<>(index, limit);
        Page<Course> courseList;
        if (queryCondition == null) {
            courseList = baseMapper.selectPage(coursePage, null);
        } else {
            // 获取查询条件
            String title = queryCondition.getTitle();
            Integer status = queryCondition.getStatus();
            String beginTime = queryCondition.getBeginTime();
            String endTime = queryCondition.getEndTime();
            // 设定查询条件
            LambdaQueryWrapper<Course> wrapper = new LambdaQueryWrapper<>();
            wrapper.like(StringUtils.hasText(title), Course::getTitle, title);
            wrapper.eq(!StringUtils.isEmpty(status), Course::getStatus, status);
            wrapper.ge(StringUtils.hasText(beginTime), Course::getCreateTime, beginTime);
            wrapper.le(StringUtils.hasText(endTime), Course::getCreateTime, endTime);
            // 查询
            courseList = baseMapper.selectPage(coursePage, wrapper);
        }
        // 显示的数据
        List<CourseListVO> courseListVOList = courseList.getRecords().stream()
                .map(item -> {
                    CourseListVO courseListVO = new CourseListVO();
                    BeanUtils.copyProperties(item, courseListVO);
                    return courseListVO;
                }).collect(Collectors.toList());
        // 还需要查询讲师姓名
        List<Teacher> teacherList = teacherService.list();
        Map<Long, Teacher> teacherMap = teacherList.stream().collect(Collectors.toMap(Teacher::getId, teacher -> teacher));
        courseListVOList.forEach(item -> {
            Teacher teacher = teacherMap.getOrDefault(item.getTeacherId(), null);
            if (teacher != null) item.setTeacherName(teacher.getName());
        });
        Map<String, Object> data = new HashMap<>();
        data.put("records", courseListVOList);
        data.put("total", courseList.getTotal());
        data.put("pages", courseList.getPages());
        return data;
    }

    /**
     * 用户端分页查询获取课程信息
     *
     * @param index          当前页
     * @param limit          每页记录数
     * @param queryCondition 查询条件
     * @return 分页查询到的课程数据
     */
    @Override
    public Map<String, Object> pageQueryCourse(Long index, Long limit, CourseUserQueryCondition queryCondition) {
        Page<Course> coursePage = new Page<>(index, limit);

        LambdaQueryWrapper<Course> wrapper = new LambdaQueryWrapper<>();
        if (queryCondition != null) {
            // 一级分类
            Long subjectId = queryCondition.getSubjectId();
            // 二级分类
            Long typeId = queryCondition.getTypeId();
            // 关注度,日期,价格排序
            String saleCountSort = queryCondition.getSaleCountSort();
            String createTimeSort = queryCondition.getCreateTimeSort();
            String priceSort = queryCondition.getPriceSort();
            // 设置查询条件
            wrapper.eq(Objects.nonNull(subjectId), Course::getSubjectId, subjectId);
            wrapper.eq(Objects.nonNull(typeId), Course::getTypeId, typeId);
            wrapper.orderByDesc(StringUtils.hasText(saleCountSort), Course::getSaleCount);
            wrapper.orderByDesc(StringUtils.hasText(createTimeSort), Course::getCreateTime);
            wrapper.orderByDesc(StringUtils.hasText(priceSort), Course::getPrice);
        }
        Page<Course> courseList = baseMapper.selectPage(coursePage, wrapper);
        // 从分页对象中取出查询出来的数据并封装分页对象
        Map<String, Object> dataMap = new HashMap<>();
        //封装到map中
        dataMap.put("total", courseList.getTotal());
        dataMap.put("records", courseList.getRecords());
        dataMap.put("pages", courseList.getPages());
        dataMap.put("current", courseList.getCurrent());
        dataMap.put("size", courseList.getSize());
        dataMap.put("hasNext", courseList.hasNext());
        dataMap.put("hasPrevious", courseList.hasPrevious());
        return dataMap;
    }

    /**
     * 根据课程id获取课程基本信息
     *
     * @param courseId 课程id
     * @return 课程基本信息
     */
    @Override
    public CourseInfoVO getCourseInfo(Long courseId) {
        // 从数据库中获取课程
        Course course = baseMapper.selectById(courseId);
        // 如果为空则说明不存在课程
        if (course == null) return null;
        CourseInfoVO courseInfo = new CourseInfoVO();
        BeanUtils.copyProperties(course, courseInfo);
        return courseInfo;
    }

    /**
     * 添加课程基本信息
     *
     * @param courseInfo 课程基本信息实体
     * @return 添加后课程的id
     */
    @Override
    public Long addCourse(CourseInfoVO courseInfo) {
        // 将CourseInfoVO转化为Course类
        Course course = new Course();
        BeanUtils.copyProperties(courseInfo, course);
        // 添加失败则抛异常
        if (baseMapper.insert(course) == 0) throw new OESException("添加课程信息失败");
        // 添加成功则返回课程id
        return course.getId();
    }

    /**
     * 根据课程id更新课程基本信息
     *
     * @param courseInfo 课程基本信息实体
     * @return 是否更新成功
     */
    @Override
    public boolean updateCourse(CourseInfoVO courseInfo) {
        // 将CourseInfoVO转换为Course类
        Course course = new Course();
        BeanUtils.copyProperties(courseInfo, course);
        return baseMapper.updateById(course) >= 1;
    }

    /**
     * 根据课程id发布课程
     *
     * @param courseId 等待发布的课程id
     * @return 是否发布成功
     */
    @Override
    public boolean publishCourse(Long courseId) {
        Course course = new Course();
        course.setId(courseId);
        course.setStatus(1);
        return baseMapper.updateById(course) >= 1;
    }

    /**
     * 获取课程发布时要显示的数据
     *
     * @param courseId 要查询的课程id
     * @return 发布课程时显示的数据
     */
    @Override
    public CoursePublishVO getPublishCourse(Long courseId) {
        return baseMapper.getPublishCourse(courseId);
    }

    /**
     * 根据课程id删除课程
     *
     * @param courseId 要删除的课程id
     * @return 是否删除成功
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteCourse(Long courseId) {
        // 删除章节(小节也会随之删除)
        if (!chapterService.deleteChapterByCourseId(courseId)) {
            throw new OESException("删除章节失败");
        }
        // 去数据库中删除课程
        return baseMapper.deleteById(courseId) >= 1;
    }

    /**
     * 获取热门课程
     *
     * @return 8条热门课程
     */
    @Cacheable(value = "course", key = "'getHotTeacherList'")
    @Override
    public List<Course> getHotCourseList() {
        LambdaQueryWrapper<Course> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Course::getViewCount);
        wrapper.orderByDesc(Course::getCreateTime);
        wrapper.last("limit 8");
        return baseMapper.selectList(wrapper);
    }

    /**
     * 根据讲师id获取其所讲课程
     *
     * @param teacherId 要查询的讲师id
     * @return 讲师所讲课程列表
     */
    @Override
    public List<Course> getCourseByTeacherId(Long teacherId) {
        LambdaQueryWrapper<Course> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Course::getTeacherId, teacherId);
        // 只选择已发布课程
        wrapper.eq(Course::getStatus, 1);
        return baseMapper.selectList(wrapper);
    }

    /**
     * 课程详情:根据路径中课程id获取课程基本信息和课程章节信息,课程所属讲师
     *
     * @param courseId 课程id
     * @param request  请求头,判断是否登录
     * @return 查到的数据并封装为map集合
     */
    @Override
    public Map<String, Object> getUserCourseInfo(Long courseId, HttpServletRequest request) {
        // 根据课程id,编写sql语句查询课程信息
        CourseUserInfoVO courseUserInfoVO = baseMapper.getUserCourseInfo(courseId);
        // 根据课程id查询章节和小节
        List<ChapterVO> chapterAndVideoList = chapterService.getAllChapterAndVideo(courseId);
        // 根据课程id和用户id查询当前课程是否已经支付
        String memberId = JwtUtil.getUserIdByJwtToken(request);
        boolean haveBuyCourse = false;
        if (StringUtils.hasText(memberId) && courseId != null) {
            haveBuyCourse = orderClient.remoteGetUserHaveBuyCourse(courseId, Long.valueOf(memberId));
        }
        // 判断用户是否收藏课程
        boolean haveCollectCourse = collectService.haveCollectCourse(Long.valueOf(memberId), courseId);
        Map<String, Object> data = new HashMap<>();
        data.put("courseUserInfoVO", courseUserInfoVO);
        data.put("chapterAndVideoList", chapterAndVideoList);
        data.put("haveBuyCourse", haveBuyCourse);
        data.put("haveCollectCourse", haveCollectCourse);
        return data;
    }

    /**
     * 根据课程id获取课程基本信息
     *
     * @param courseId 课程id
     * @return 课程信息
     */
    @Override
    public CourseUserInfoVO getUserCourseInfoById(Long courseId) {
        return baseMapper.getUserCourseInfo(courseId);
    }

    /**
     * 根据用户id分页查询收藏课程
     *
     * @param userId 用户id
     * @param index  当前页
     * @param limit  每页记录数
     * @return 用户收藏课程
     */
    @Override
    public Map<String, Object> pageQueryCollectCourse(Long userId, Long index, Long limit) {
        Page<CourseCollect> courseCollectPage = new Page<>(index, limit);
        LambdaQueryWrapper<CourseCollect> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CourseCollect::getMemberId, userId);
        wrapper.select(CourseCollect::getCourseId);
        Page<CourseCollect> courseIdList = collectService.page(courseCollectPage, wrapper);
        // 然后将课程id和课程实体关联
        List<Course> allCourseList = baseMapper.selectList(null);
        Map<Long, Course> courseIdMap = allCourseList.stream().collect(Collectors.toMap(Course::getId, course -> course));
        // 转换为最终课程列表
        List<Course> finalCourseList = courseIdList.getRecords().stream()
                .map(course -> courseIdMap.get(course.getCourseId()))
                .collect(Collectors.toList());
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("total", courseIdList.getTotal());
        dataMap.put("records", finalCourseList);
        dataMap.put("pages", courseIdList.getPages());
        dataMap.put("current", courseIdList.getCurrent());
        dataMap.put("size", courseIdList.getSize());
        dataMap.put("hasNext", courseIdList.hasNext());
        dataMap.put("hasPrevious", courseIdList.hasPrevious());
        return dataMap;
    }

    /**
     * 收藏课程
     *
     * @param courseId 课程id
     * @param memberId 用户id
     */
    @Override
    public void collectCourse(Long courseId, Long memberId) {
        CourseCollect courseCollect = new CourseCollect();
        courseCollect.setCourseId(courseId);
        courseCollect.setMemberId(memberId);
        collectService.save(courseCollect);
    }

    /**
     * 取消收藏课程
     *
     * @param courseId 课程id
     * @param memberId 用户id
     */
    @Override
    public void cancelCollectCourse(Long courseId, Long memberId) {
        LambdaQueryWrapper<CourseCollect> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CourseCollect::getCourseId, courseId);
        wrapper.eq(CourseCollect::getMemberId, memberId);
        collectService.remove(wrapper);
    }
}
