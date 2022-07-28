package com.zh.oes.edu.service;

import com.zh.oes.model.entity.edu.Chapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.oes.model.vo.edu.admin.ChapterVO;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-23
 */
public interface ChapterService extends IService<Chapter> {
    // 根据课程id获取所有课程章节和小节
    List<ChapterVO> getAllChapterAndVideo(Long courseId);

    // 根据id删除章节,包括下面的小节(视频)
    boolean deleteChapter(Long chapterId);

    // 根据课程删除章节
    boolean deleteChapterByCourseId(Long courseId);
}
