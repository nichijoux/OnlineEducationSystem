package com.zh.oes.model.vo.edu.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value = "学习记录信息VO", description = "网站学习记录需要的相关字段")
public class StudyRecordVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程id")
    private Long id;

    @ApiModelProperty(value = "课程标题")
    private String title;

    @ApiModelProperty(value = "小节名称")
    private String videoName;

    @ApiModelProperty(value = "阿里云视频id")
    private String videoSourceId;

    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;

    @ApiModelProperty(value = "学习时间", example = "2022-6-24 12:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date studyTime;
}
