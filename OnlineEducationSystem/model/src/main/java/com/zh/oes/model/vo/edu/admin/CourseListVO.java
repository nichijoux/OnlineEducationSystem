package com.zh.oes.model.vo.edu.admin;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@ApiModel(value = "管理员端查看课程列表显示的数据", description = "管理员端查看课程列表显示的数据")
public class CourseListVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("课程ID")
    private Long id;

    @ApiModelProperty("课程标题")
    private String title;

    @ApiModelProperty("课程讲师ID")
    private Long teacherId;

    @ApiModelProperty(value = "课程讲师名称")
    private String teacherName;

    @ApiModelProperty("课程封面图片路径")
    private String cover;

    @ApiModelProperty("课程描述")
    private String description;

    @ApiModelProperty("一级分类id")
    private Long subjectId;

    @ApiModelProperty("二级分类id")
    private Long typeId;

    @ApiModelProperty("课程销售价格，设置为0则可免费观看")
    private BigDecimal price;

    @ApiModelProperty("总课时")
    private Integer lessonNum;

    @ApiModelProperty("销售数量")
    private Long saleCount;

    @ApiModelProperty("浏览数量")
    private Long viewCount;

    @ApiModelProperty("课程状态 0未发布 1已发布")
    private Integer status;

    @ApiModelProperty("逻辑删除 1（true）已删除， 0（false）未删除")
    private Integer isDeleted;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}