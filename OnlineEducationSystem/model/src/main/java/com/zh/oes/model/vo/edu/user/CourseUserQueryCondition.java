package com.zh.oes.model.vo.edu.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "CourseUserQueryCondition", description = "前端查询课程时的条件对象")
public class CourseUserQueryCondition implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程名称")
    private String title;

    @ApiModelProperty(value = "讲师id")
    private Long teacherId;

    @ApiModelProperty(value = "一级类别id")
    private Long subjectId;

    @ApiModelProperty(value = "二级类别id")
    private Long typeId;

    @ApiModelProperty(value = "销量排序")
    private String saleCountSort;

    @ApiModelProperty(value = "最新时间排序")
    private String createTimeSort;

    @ApiModelProperty(value = "价格排序")
    private String priceSort;
}
