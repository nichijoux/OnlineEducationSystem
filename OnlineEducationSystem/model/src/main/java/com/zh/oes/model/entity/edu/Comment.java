package com.zh.oes.model.entity.edu;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 评论
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-23
 */
@Data
@TableName("edu_comment")
@ApiModel(value = "Comment对象", description = "评论")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("评论id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("课程id")
    @NotNull(message = "课程id不能为空")
    @TableField(value = "course_id")
    private Long courseId;

    @ApiModelProperty("会员id")
    @NotNull(message = "会员id不能为空")
    @TableField(value = "member_id")
    private Long memberId;

    @ApiModelProperty("会员昵称")
    @NotBlank(message = "会员昵称不能为空")
    @TableField(value = "nickname")
    private String nickname;

    @ApiModelProperty("会员头像")
    @TableField(value = "avatar")
    private String avatar;

    @ApiModelProperty("评论内容")
    @NotBlank(message = "评论内容不能为空")
    @TableField(value = "content")
    private String content;

    @ApiModelProperty("评论星级")
    @TableField(value = "star")
    private Integer star;

    @ApiModelProperty("逻辑删除 1（true）已删除， 0（false）未删除")
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
