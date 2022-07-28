package com.zh.oes.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.oes.model.entity.edu.Comment;
import com.zh.oes.model.vo.edu.admin.CommentQueryCondition;
import com.zh.oes.model.vo.edu.user.CommentVO;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 评论 服务类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-23
 */
public interface CommentService extends IService<Comment> {
    // 分页查询评论内容
    Page<Comment> pageQueryComment(Long courseId, Long index, Long limit);

    // 管理员分页查询评论内容
    Map<String,Object> pageQueryComment(Long index, Long limit, CommentQueryCondition queryCondition);

    // 添加评论内容
    boolean addComment(CommentVO comment, HttpServletRequest request);
}
