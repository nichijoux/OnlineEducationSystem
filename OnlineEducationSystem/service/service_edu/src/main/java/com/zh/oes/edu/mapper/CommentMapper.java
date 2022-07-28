package com.zh.oes.edu.mapper;

import com.zh.oes.model.entity.edu.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 评论 Mapper 接口
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-23
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}
