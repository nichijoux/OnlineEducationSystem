package com.zh.oes.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.oes.common.base.client.UcenterClient;
import com.zh.oes.edu.mapper.StudyRecordMapper;
import com.zh.oes.edu.service.StudyRecordService;
import com.zh.oes.model.entity.edu.StudyRecord;
import com.zh.oes.model.vo.edu.admin.StudyRecordQueryCondition;
import com.zh.oes.model.vo.edu.user.MemberVO;
import com.zh.oes.model.vo.edu.user.StudyRecordVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 学生学习记录 服务实现类
 * </p>
 *
 * @author nichijoux
 * @since 2022-08-14
 */
@Service
public class StudyRecordServiceImpl extends ServiceImpl<StudyRecordMapper, StudyRecord> implements StudyRecordService {

    private UcenterClient ucenterClient;

    @Autowired
    public void setUcenterClient(UcenterClient ucenterClient) {
        this.ucenterClient = ucenterClient;
    }

    /**
     * 添加学习记录
     *
     * @param teacherName   讲师姓名
     * @param courseName    课程名
     * @param courseCover   课程封面
     * @param videoName     小节名
     * @param videoSourceId 阿里云视频id
     * @param courseId      课程id
     * @param userId        用户id
     */
    @Override
    public void addStudyRecord(String teacherName, String courseName, String courseCover, String videoName, String videoSourceId, Long courseId, Long userId) {
        MemberVO memberVO = ucenterClient.remoteGetUser(userId);
        String nickname = memberVO.getNickname();
        StudyRecord record = new StudyRecord();
        record.setTeacherName(teacherName);
        record.setCourseName(courseName);
        record.setCourseCover(courseCover);
        record.setVideoName(videoName);
        record.setNickname(nickname);
        record.setCourseId(courseId);
        record.setVideoSourceId(videoSourceId);
        record.setMemberId(userId);
        baseMapper.insert(record);
    }

    /**
     * 分页查询学习记录
     *
     * @param index  当前页
     * @param limit  每页记录数
     * @param userId 用户id
     * @return 分页数据
     */
    @Override
    public Map<String, Object> pageQueryUserStudyRecord(Long index, Long limit, Long userId) {
        Page<StudyRecord> studyRecordPage = new Page<>(index, limit);
        LambdaQueryWrapper<StudyRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(StudyRecord::getCreateTime);
        wrapper.eq(StudyRecord::getMemberId, userId);
        Page<StudyRecord> studyRecordList = baseMapper.selectPage(studyRecordPage, wrapper);
        // 封装为VO对象
        List<StudyRecordVO> studyList = studyRecordList.getRecords().stream().map(record -> {
            StudyRecordVO studyRecordVO = new StudyRecordVO();
            studyRecordVO.setCover(record.getCourseCover());
            studyRecordVO.setId(record.getCourseId());
            studyRecordVO.setTitle(record.getCourseName());
            studyRecordVO.setVideoName(record.getVideoName());
            studyRecordVO.setVideoSourceId(record.getVideoSourceId());
            studyRecordVO.setStudyTime(record.getCreateTime());
            return studyRecordVO;
        }).collect(Collectors.toList());
        // 封装分页数据
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("total", studyRecordList.getTotal());
        dataMap.put("records", studyList);
        dataMap.put("pages", studyRecordList.getPages());
        dataMap.put("current", studyRecordList.getCurrent());
        dataMap.put("size", studyRecordList.getSize());
        dataMap.put("hasNext", studyRecordList.hasNext());
        dataMap.put("hasPrevious", studyRecordList.hasPrevious());
        return dataMap;
    }

    /**
     * 管理员分页查询学习记录
     *
     * @param index          当前页
     * @param limit          每页记录数
     * @param queryCondition 查询条件
     * @return 分页数据
     */
    @Override
    public Page<StudyRecord> pageQueryStudyRecord(Long index, Long limit, StudyRecordQueryCondition queryCondition) {
        Page<StudyRecord> studyRecordPage = new Page<>(index, limit);
        LambdaQueryWrapper<StudyRecord> wrapper = new LambdaQueryWrapper<>();
        if (queryCondition != null) {
            // 获取查询条件
            String nickname = queryCondition.getNickname();
            String courseName = queryCondition.getCourseName();
            String beginTime = queryCondition.getBeginTime();
            String endTime = queryCondition.getEndTime();
            // 设定查询条件
            wrapper.like(StringUtils.hasText(nickname), StudyRecord::getNickname, nickname);
            wrapper.like(StringUtils.hasText(courseName), StudyRecord::getCourseName, courseName);
            wrapper.ge(StringUtils.hasText(beginTime), StudyRecord::getCreateTime, beginTime);
            wrapper.le(StringUtils.hasText(endTime), StudyRecord::getCreateTime, endTime);
        }
        return baseMapper.selectPage(studyRecordPage, wrapper);
    }
}
