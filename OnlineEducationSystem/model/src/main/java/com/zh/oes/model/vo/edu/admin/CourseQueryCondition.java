package com.zh.oes.model.vo.edu.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "Course查询对象", description = "查询课程对象封装")
public class CourseQueryCondition implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程名")
    private String title;

    @ApiModelProperty(value = "课程状态")
    private Integer status;

    @ApiModelProperty(value = "查询开始时间", example = "2022-6-24 12:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String beginTime;

    @ApiModelProperty(value = "查询结束时间", example = "2022-6-24 12:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String endTime;
}
