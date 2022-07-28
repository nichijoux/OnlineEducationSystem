package com.zh.oes.edu.controller.user;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.oes.common.utils.Result;
import com.zh.oes.edu.service.CommentService;
import com.zh.oes.model.entity.edu.Comment;
import com.zh.oes.model.vo.edu.user.CommentVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 评论 前端控制器
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-23
 */
@Api(tags = "评论前台api接口")
@RestController
@RequestMapping("user/edu/comment")
public class CommentUserController {
    private CommentService commentService;

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @ApiOperation(value = "分页查询评论内容")
    @GetMapping("pageQueryComment/{courseId}/{index}/{limit}")
    public Result pageQueryComment(
            @ApiParam(name = "courseId", value = "课程id", required = true)
            @PathVariable Long courseId,
            @ApiParam(name = "index", value = "当前页", required = true)
            @PathVariable Long index,
            @ApiParam(name = "limit", value = "最大显示数", required = true)
            @PathVariable Long limit) {
        Page<Comment> commentList = commentService.pageQueryComment(courseId, index, limit);
        Map<String, Object> data = new HashMap<>();
        data.put("records", commentList.getRecords());
        data.put("current", commentList.getCurrent());
        data.put("total", commentList.getTotal());
        data.put("pages", commentList.getPages());
        data.put("hasPrevious", commentList.hasPrevious());
        data.put("hasNext", commentList.hasNext());
        return Result.success(data);
    }

    @ApiOperation(value = "添加评论信息")
    @PostMapping("addComment")
    public Result addComment(
            @ApiParam(name = "comment", value = "评论信息", required = true)
            @Validated @RequestBody CommentVO comment,
            @ApiParam(name = "request", value = "请求体,用于判断是否登录", required = true)
                    HttpServletRequest request) {
        return commentService.addComment(comment, request) ?
                Result.success() : Result.failure().message("添加评论失败");
    }
}

