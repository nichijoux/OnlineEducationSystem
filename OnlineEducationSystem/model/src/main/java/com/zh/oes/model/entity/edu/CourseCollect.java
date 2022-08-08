package com.zh.oes.model.entity.edu;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 课程收藏
 * </p>
 *
 * @author nichijoux
 * @since 2022-08-08
 */
@Getter
@Setter
@TableName("edu_course_collect")
@ApiModel(value = "CourseCollect对象", description = "课程收藏")
public class CourseCollect implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("收藏ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("课程id")
    @TableField(value = "course_id")
    private Long courseId;

    @ApiModelProperty("会员id")
    @TableField(value = "member_id")
    private Long memberId;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
