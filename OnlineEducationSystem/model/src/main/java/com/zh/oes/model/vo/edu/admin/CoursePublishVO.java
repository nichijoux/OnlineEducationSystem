package com.zh.oes.model.vo.edu.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "课程发布的VO对象", description = "课程发布时前后端交互的对象")
public class CoursePublishVO {
    @ApiModelProperty(value = "课程id")
    private Long id;

    @ApiModelProperty(value = "课程名称")
    private String title;

    @ApiModelProperty(value = "课程封面")
    private String cover;

    @ApiModelProperty(value = "课时数")
    private Integer lessonNum;

    @ApiModelProperty(value = "一级分类名称")
    private String subjectName;

    @ApiModelProperty(value = "二级分类名称")
    private String typeName;

    @ApiModelProperty(value = "讲师名称")
    private String teacherName;

    @ApiModelProperty(value = "课程价格,只用于显示")
    private String price;
}
