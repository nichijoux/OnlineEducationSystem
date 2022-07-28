package com.zh.oes.edu.controller.admin;

import com.zh.oes.common.utils.Result;
import com.zh.oes.edu.service.CommentService;
import com.zh.oes.model.vo.edu.admin.CommentQueryCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Api(tags = "评论后台api接口")
@RestController
@RequestMapping("admin/edu/comment")
public class CommentAdminController {
    private CommentService commentService;

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }

    @ApiOperation(value = "分页查询评论内容")
    @PostMapping("pageQueryComment/{index}/{limit}")
    public Result pageQueryComment(
            @ApiParam(name = "index", value = "当前页", required = true)
            @PathVariable Long index,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "queryCondition", value = "查询条件")
            @Validated @RequestBody(required = false) CommentQueryCondition queryCondition) {
        Map<String, Object> data = commentService.pageQueryComment(index, limit, queryCondition);
        return Result.success(data);
    }

    @ApiOperation(value = "根据id删除评论")
    @DeleteMapping("deleteComment/{commentId}")
    public Result deleteComment(
            @ApiParam(name = "commentId", value = "要删除的评论id", required = true)
            @PathVariable Long commentId) {
        return commentService.removeById(commentId) ?
                Result.success() : Result.failure().message("删除评论失败");
    }
}
