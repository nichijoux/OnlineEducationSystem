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
 * 学生学习记录
 * </p>
 *
 * @author nichijoux
 * @since 2022-08-14
 */
@Getter
@Setter
@TableName("student_study_record")
@ApiModel(value = "StudyRecord对象", description = "学生学习记录")
public class StudyRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("用户id")
    @TableField("member_id")
    private Long memberId;

    @ApiModelProperty("课程id")
    @TableField("course_id")
    private Long courseId;

    @ApiModelProperty("阿里云视频id")
    @TableField("video_source_id")
    private String videoSourceId;

    @ApiModelProperty("用户昵称")
    @TableField("nickname")
    private String nickname;

    @ApiModelProperty("课程名")
    @TableField("course_name")
    private String courseName;

    @ApiModelProperty("课程封面")
    @TableField("course_cover")
    private String courseCover;

    @ApiModelProperty("小节名")
    @TableField("video_name")
    private String videoName;

    @ApiModelProperty("讲师姓名")
    @TableField("teacher_name")
    private String teacherName;

    @ApiModelProperty("创建时间（学习时间）")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
