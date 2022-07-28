package com.zh.oes.edu.controller.admin;


import com.zh.oes.common.utils.Result;
import com.zh.oes.edu.service.ChapterService;
import com.zh.oes.model.entity.edu.Chapter;
import com.zh.oes.model.vo.edu.admin.ChapterVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-23
 */
@Api(tags = "章节后台api接口")
@RestController
@RequestMapping("admin/edu/chapter")
public class ChapterAdminController {
    private ChapterService chapterService;

    @Autowired
    public void setChapterService(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @ApiOperation(value = "根据课程id获取所有课程章节和小节")
    @GetMapping("getAllChapterAndVideo/{courseId}")
    public Result getAllChapterAndVideo(
            @ApiParam(name = "courseId", value = "要查询的课程id", required = true)
            @PathVariable Long courseId) {
        List<ChapterVO> chapterVideoList = chapterService.getAllChapterAndVideo(courseId);
        return Result.success(chapterVideoList);
    }

    @ApiOperation(value = "根据章节id获取章节信息")
    @GetMapping("getChapter/{chapterId}")
    public Result getChapter(
            @ApiParam(name = "chapterId", value = "章节id", required = true)
            @PathVariable Long chapterId) {
        Chapter chapter = chapterService.getById(chapterId);
        return chapter != null ?
                Result.success(chapter) : Result.failure().message("章节不存在");
    }

    @ApiOperation(value = "添加章节")
    @PostMapping("addChapter")
    public Result addChapter(
            @ApiParam(name = "chapter", value = "要添加的章节实体", required = true)
            @Validated @RequestBody Chapter chapter) {
        return chapterService.save(chapter) ?
                Result.success() : Result.failure().message("添加章节失败");
    }

    @ApiOperation(value = "修改章节信息")
    @PostMapping("updateChapter")
    public Result updateChapter(
            @ApiParam(name = "chapter", value = "要修改的章节实体", required = true)
            @Validated @RequestBody Chapter chapter) {
        return chapterService.updateById(chapter) ?
                Result.success() : Result.failure().message("修改章节失败");
    }

    @ApiOperation(value = "根据id删除章节,包括下面的小节(视频)")
    @DeleteMapping("deleteChapter/{chapterId}")
    public Result deleteChapter(
            @ApiParam(name = "chapterId", value = "要删除的章节id", required = true)
            @PathVariable Long chapterId) {
        return chapterService.deleteChapter(chapterId) ?
                Result.success() : Result.failure().message("删除章节和小节失败");
    }
}

