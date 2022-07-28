package com.zh.oes.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.oes.common.base.exception.OESException;
import com.zh.oes.edu.mapper.ChapterMapper;
import com.zh.oes.edu.service.ChapterService;
import com.zh.oes.edu.service.VideoService;
import com.zh.oes.model.entity.edu.Chapter;
import com.zh.oes.model.entity.edu.Video;
import com.zh.oes.model.vo.edu.admin.ChapterVO;
import com.zh.oes.model.vo.edu.admin.VideoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-23
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {
    private VideoService videoService;

    @Autowired
    public void setVideoService(VideoService videoService) {
        this.videoService = videoService;
    }

    /**
     * 根据课程id获取所有课程章节和小节
     *
     * @param courseId 要查询的课程id
     * @return 封装好的, 包括所有课程章节以及小节的章节列表
     */
    @Override
    public List<ChapterVO> getAllChapterAndVideo(Long courseId) {
        // 根据课程id获取所有章节
        LambdaQueryWrapper<Chapter> chapterLambdaQueryWrapper = new LambdaQueryWrapper<>();
        chapterLambdaQueryWrapper.eq(Chapter::getCourseId, courseId);
        // 按sort排序
        chapterLambdaQueryWrapper.orderByAsc(Chapter::getSort);
        List<Chapter> chapterList = baseMapper.selectList(chapterLambdaQueryWrapper);

        // 根据课程id获取所有小节
        LambdaQueryWrapper<Video> videoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        videoLambdaQueryWrapper.eq(Video::getCourseId, courseId);
        // 按sort排序
        videoLambdaQueryWrapper.orderByAsc(Video::getSort);
        List<Video> videoList = videoService.list(videoLambdaQueryWrapper);

        // 封装章节,将小节设置到章节下
        return chapterList.stream().map(chapter -> {
            ChapterVO chapterVO = new ChapterVO();
            BeanUtils.copyProperties(chapter, chapterVO);
            // 过滤得到章节下的小节,并将其转化为VideoVO类,最终收集为列表
            List<VideoVO> videoVOList = videoList.stream().filter(video -> video.getChapterId().equals(chapter.getId()))
                    .map(video -> {
                        VideoVO videoVO = new VideoVO();
                        BeanUtils.copyProperties(video, videoVO);
                        return videoVO;
                    })
                    .collect(Collectors.toList());
            chapterVO.setChildren(videoVOList);
            return chapterVO;
        }).collect(Collectors.toList());
    }

    /**
     * 根据id删除章节,包括下面的小节(视频)
     *
     * @param chapterId 要删除的章节id
     * @return 是否删除成功
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteChapter(Long chapterId) {
        if (!videoService.deleteVideoByChapterId(chapterId))
            throw new OESException("根据章节id删除小节视频失败");
        // 删除后再删除章节
        return baseMapper.deleteById(chapterId) >= 1;
    }

    /**
     * 根据课程id删除章节,包括下面的小节(视频)
     *
     * @param courseId 要删除的课程id
     * @return 是否删除成功
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteChapterByCourseId(Long courseId) {
        if (!videoService.deleteVideoByCourseId(courseId)) {
            throw new OESException("删除小节(视频失败)");
        }
        // 再去数据库中删除章节
        LambdaQueryWrapper<Chapter> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Chapter::getCourseId, courseId);
        Long count = baseMapper.selectCount(wrapper);
        if (count == 0) return true;
        return baseMapper.delete(wrapper) >= 1;
    }
}
