package com.zh.oes.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.oes.common.base.exception.OESException;
import com.zh.oes.common.utils.JwtUtil;
import com.zh.oes.common.base.client.UcenterClient;
import com.zh.oes.edu.mapper.CommentMapper;
import com.zh.oes.edu.service.CommentService;
import com.zh.oes.edu.service.CourseService;
import com.zh.oes.model.entity.edu.Comment;
import com.zh.oes.model.entity.edu.Course;
import com.zh.oes.model.vo.edu.admin.CommentListVO;
import com.zh.oes.model.vo.edu.admin.CommentQueryCondition;
import com.zh.oes.model.vo.edu.user.CommentVO;
import com.zh.oes.model.vo.edu.user.MemberVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 评论 服务实现类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-23
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    private CourseService courseService;

    private UcenterClient ucenterClient;

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @Autowired
    public void setUcenterClient(UcenterClient ucenterClient) {
        this.ucenterClient = ucenterClient;
    }

    /**
     * 分页查看评论内容
     *
     * @param courseId 要查看评论的课程id
     * @param index    当前页
     * @param limit    每页记录数
     * @return 分页查询得到的评论数据
     */
    @Override
    public Page<Comment> pageQueryComment(Long courseId, Long index, Long limit) {
        Page<Comment> commentPage = new Page<>(index, limit);
        // 根据课程id查询该课程的所有评论
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Comment::getCourseId, courseId);
        // 按时间排序
        wrapper.orderByDesc(Comment::getCreateTime);
        return baseMapper.selectPage(commentPage, wrapper);
    }

    /**
     * 管理员分页查询评论内容
     *
     * @param index          当前页
     * @param limit          每页记录数
     * @param queryCondition 评论查询条件
     * @return 分页查询获得的评论列表
     */
    @Override
    public Map<String, Object> pageQueryComment(Long index, Long limit, CommentQueryCondition queryCondition) {
        Page<Comment> commentPage = new Page<>(index, limit);
        // 查询所有的课程评论
        LambdaQueryWrapper<Comment> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Comment::getCreateTime);
        if (queryCondition != null) {
            String nickname = queryCondition.getNickname();
            Integer star = queryCondition.getStar();
            String beginTime = queryCondition.getBeginTime();
            String endTime = queryCondition.getEndTime();
            wrapper.like(StringUtils.hasText(nickname), Comment::getNickname, nickname);
            wrapper.eq(!StringUtils.isEmpty(star), Comment::getStar, star);
            wrapper.ge(StringUtils.hasText(beginTime), Comment::getCreateTime, beginTime);
            wrapper.le(StringUtils.hasText(endTime), Comment::getCreateTime, endTime);
        }
        Page<Comment> commentList = baseMapper.selectPage(commentPage, wrapper);

        // 查询comment对应的课程名
        List<Course> allCourseList = courseService.list();
        Map<Long, Course> courseMap = allCourseList.stream()
                .collect(Collectors.toMap(Course::getId, course -> course));

        // 将Comment转换为CommentListVO
        List<CommentListVO> commentListVOList = commentList.getRecords().stream().map(comment -> {
            CommentListVO commentListVO = new CommentListVO();
            BeanUtils.copyProperties(comment, commentListVO);
            // 查找课程名
            Course course = courseMap.getOrDefault(comment.getCourseId(), null);
            if (course != null) {
                commentListVO.setCourseName(course.getTitle());
                commentListVO.setCourseCover(course.getCover());
            }
            return commentListVO;
        }).collect(Collectors.toList());

        Map<String, Object> data = new HashMap<>();
        data.put("records", commentListVOList);
        data.put("total", commentList.getTotal());
        data.put("size", commentList.getSize());
        return data;
    }

    /**
     * 添加评论内容
     *
     * @param commentVO 要添加的评论实体
     * @param request   请求体,用于判断是否登录
     * @return 是否添加成功
     */
    @Override
    public boolean addComment(CommentVO commentVO, HttpServletRequest request) {
        // 先去请求头中获取有没有token
        String memberId = JwtUtil.getUserIdByJwtToken(request);
        if (StringUtils.isEmpty(memberId)) {
            throw new OESException("请登录");
        }

        // 如果已经登录,则生成要添加的comment实体
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentVO, comment);

        // 设置comment的memberId
        comment.setMemberId(Long.valueOf(memberId));

        MemberVO memberVO = ucenterClient.remoteGetUser(Long.valueOf(memberId));
        if (memberVO != null) {
            comment.setAvatar(memberVO.getAvatar());
            comment.setNickname(memberVO.getNickname());
            return baseMapper.insert(comment) >= 1;
        }
        return false;
    }
}
