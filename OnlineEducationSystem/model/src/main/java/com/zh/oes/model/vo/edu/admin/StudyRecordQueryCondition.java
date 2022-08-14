package com.zh.oes.model.vo.edu.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "学习记录查询对象", description = "学习记录查询对象封装")
public class StudyRecordQueryCondition {
    @ApiModelProperty(value = "评论用户昵称")
    private String nickname;

    @ApiModelProperty(value = "课程名称")
    private String courseName;

    @ApiModelProperty(value = "查询开始时间", example = "2022-6-24 12:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String beginTime;

    @ApiModelProperty(value = "查询结束时间", example = "2022-6-24 12:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String endTime;
}
