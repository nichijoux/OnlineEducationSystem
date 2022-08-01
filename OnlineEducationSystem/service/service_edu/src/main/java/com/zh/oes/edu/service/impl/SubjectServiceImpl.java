package com.zh.oes.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.oes.common.base.exception.OESException;
import com.zh.oes.edu.mapper.SubjectMapper;
import com.zh.oes.edu.service.SubjectService;
import com.zh.oes.model.entity.edu.LevelSubject;
import com.zh.oes.model.entity.edu.Subject;
import com.zh.oes.model.vo.edu.admin.SubjectDownloadVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-23
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {
    /**
     * 获取所有的科目,并将其按树形结构封装
     *
     * @return 封装好的学科列表
     */
    @Override
    public List<LevelSubject> getAllSubject() {
        // 获取所有学科,并按sort排序
        LambdaQueryWrapper<Subject> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(Subject::getSort);
        List<Subject> allSubject = baseMapper.selectList(wrapper);
        // 过滤获取一级学科
        List<Subject> oneSubjectList = allSubject.stream().filter(item -> item.getPid().equals(0L))
                .collect(Collectors.toList());
        // 递归获取最终集合
        return buildLevelSubject(oneSubjectList, allSubject, 0);
    }

    /**
     * 获取学科pid为{pid}的节点及其子节点
     *
     * @param pid 要获取的学科的pid
     * @return 查找到的内容
     */
    @Override
    public List<LevelSubject> getChildSubject(Long pid) {
        LambdaQueryWrapper<Subject> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(Subject::getSort);
        List<Subject> allSubject = baseMapper.selectList(wrapper);
        // 过滤获取该级学科
        List<Subject> subjectList = allSubject.stream().filter(item -> item.getPid().equals(pid))
                .collect(Collectors.toList());
        // 递归获取最终集合
        return buildLevelSubject(subjectList, allSubject, 0);
    }

    /**
     * 在subject的父节点下添加一个新的科目
     *
     * @param subject 要添加的科目节点
     * @return 是否添加成功
     */
    @Override
    public boolean addChildSubject(Subject subject) {
        // 先找到父节点所对应的子节点
        LambdaQueryWrapper<Subject> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Subject::getPid, subject.getPid());
        List<Subject> childList = baseMapper.selectList(wrapper);
        childList.forEach(item -> {
            if (item.getTitle().equals(subject.getTitle()))
                throw new OESException("该分类下已经存在科目：" + subject.getTitle());
        });
        return baseMapper.insert(subject) >= 1;
    }

    /**
     * 根据学科id删除学科分类及其子节点
     *
     * @param subjectId 要删除的子节点
     * @return 是否删除成功
     */
    @Override
    public boolean deleteSubject(Long subjectId) {
        List<Subject> allSubject = baseMapper.selectList(null);
        List<Long> idList = getChildIdList(subjectId, allSubject);
        idList.add(subjectId);
        return baseMapper.deleteBatchIds(idList) >= 1;
    }

    /**
     * 下载科目为excel文件
     */
    @Override
    public void downloadSubject(HttpServletResponse response) {
        List<LevelSubject> allSubject = getAllSubject();
        // 将LevelSubject转换为List<SubjectDownloadVO>对象
        List<SubjectDownloadVO> downloadVOList = new ArrayList<>();
        for (LevelSubject levelSubject : allSubject) {
            convertSubjectDownloadVO(null, levelSubject, downloadVOList);
        }
        try {
            String fileName = URLEncoder.encode("学科列表", "UTF-8").replaceAll("\\+", "%20");
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream(), SubjectDownloadVO.class).build();
            excelWriter.write(downloadVOList, EasyExcel.writerSheet("学科列表").build());
            excelWriter.finish();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将List<LevelSubject>转化为List<SubjectDownloadVO>
     *
     * @param parentSubject  父LevelSubject
     * @param currentSubject 当前LevelSubject
     * @param downloadVOList 要转为的SubjectDownloadVO列表
     */
    private void convertSubjectDownloadVO(LevelSubject parentSubject, LevelSubject currentSubject, List<SubjectDownloadVO> downloadVOList) {
        SubjectDownloadVO downloadVO = new SubjectDownloadVO();
        if (parentSubject != null) {
            downloadVO.setParentSubject(parentSubject.getTitle());
        }
        downloadVO.setSubject(currentSubject.getTitle());
        downloadVOList.add(downloadVO);
        List<LevelSubject> children = currentSubject.getChildren();
        if (children == null) return;
        for (LevelSubject childSubject : children) {
            convertSubjectDownloadVO(currentSubject, childSubject, downloadVOList);
        }
    }

    /**
     * 获取所有子节点(包括子子节点)的id
     *
     * @param subjectId  当前节点
     * @param allSubject 所有科目列表
     * @return 所有子节点(包括子子节点)的id
     */
    private List<Long> getChildIdList(Long subjectId, List<Subject> allSubject) {
        List<Long> idList = new ArrayList<>();
        List<Long> childIdList = allSubject.stream().filter(item -> item.getPid().equals(subjectId))
                .map(item -> {
                    List<Long> childChildIdList = getChildIdList(item.getId(), allSubject);
                    idList.addAll(childChildIdList);
                    return item.getId();
                })
                .collect(Collectors.toList());
        idList.addAll(childIdList);
        return idList;
    }

    /**
     * 递归函数,获取各层科目级别
     *
     * @param currentSubjectList 当前Subject列表
     * @param allSubject         所有的科目/类别集合
     * @return 当前Subject列表经过封装后的集合
     */
    private List<LevelSubject> buildLevelSubject(List<Subject> currentSubjectList, List<Subject> allSubject, int currentLevel) {
        List<LevelSubject> currentLevelSubjectList = new ArrayList<>();
        for (Subject subject : currentSubjectList) {
            // 将Subject转化为LevelSubject
            LevelSubject currentLevelSubject = new LevelSubject();
            currentLevelSubject.setLevel(currentLevel);
            BeanUtils.copyProperties(subject, currentLevelSubject);
            // 过滤获取下层subject的子学科
            List<Subject> nextSubjectList = allSubject.stream().filter(item -> item.getPid().equals(subject.getId()))
                    .collect(Collectors.toList());
            // 递归查询下一层是否还有子对象,
            List<LevelSubject> nextLevelSubjectList = buildLevelSubject(nextSubjectList, allSubject, currentLevel + 1);
            // 如果有子对象,则直接加入
            if (!nextLevelSubjectList.isEmpty()) currentLevelSubject.setChildren(nextLevelSubjectList);
            // 加入到该层列表
            currentLevelSubjectList.add(currentLevelSubject);
        }
        return currentLevelSubjectList;
    }
}
