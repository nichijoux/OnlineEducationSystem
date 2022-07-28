package com.zh.oes.model.vo.edu.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel(value = "课程基本信息", description = "编辑课程基本信息的表单对象")
public class CourseInfoVO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程ID")
    private Long id;

    @ApiModelProperty(value = "课程讲师ID")
    @NotNull(message = "课程讲师id不能为空")
    private Long teacherId;

    @ApiModelProperty(value = "课程专业父级ID")
    @NotNull(message = "一级分类id")
    private Long subjectId;

    @ApiModelProperty(value = "课程专业ID")
    @NotNull(message = "二级分类id")
    private Long typeId;

    @ApiModelProperty(value = "课程标题")
    @NotBlank(message = "课程标题不能为空")
    private String title;

    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    @Min(value = 0, message = "课程销售价格不能为负数")
    private BigDecimal price;

    @ApiModelProperty(value = "总课时")
    @Min(value = 1, message = "课程总课时不能为0或负数")
    private Integer lessonNum;

    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;

    @ApiModelProperty(value = "课程简介")
    @NotBlank(message = "课程简介不能为空")
    private String description;
}
