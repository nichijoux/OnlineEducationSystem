package com.zh.oes.model.vo.edu.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Data
@ApiModel(value = "Teacher查询对象", description = "讲师查询对象封装")
public class TeacherQueryCondition implements Serializable {
    @ApiModelProperty(value = "教师名称,模糊查询")
    private String name;

    @ApiModelProperty(value = "头衔,1:高级讲师 2:首席讲师")
    @Min(value = 1, message = "最低为1")
    @Max(value = 2, message = "最高为2")
    private Integer level;

    @ApiModelProperty(value = "查询开始时间", example = "2022-6-24 12:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String beginTime;

    @ApiModelProperty(value = "查询结束时间", example = "2022-6-24 12:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String endTime;
}
