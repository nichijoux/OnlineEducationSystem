package com.zh.oes.model.entity.edu;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 课程
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-21
 */
@Data
@TableName("edu_course")
@ApiModel(value = "EduCourseEntity对象", description = "课程")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("课程ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("课程标题")
    @TableField(value = "title")
    private String title;

    @ApiModelProperty("课程讲师ID")
    @TableField(value = "teacher_id")
    private Long teacherId;

    @ApiModelProperty("课程封面图片路径")
    @TableField(value = "cover")
    private String cover;

    @ApiModelProperty("课程描述")
    @TableField(value = "description")
    private String description;

    @ApiModelProperty("一级分类id")
    @TableField(value = "subject_id")
    private Long subjectId;

    @ApiModelProperty("二级分类id")
    @TableField(value = "type_id")
    private Long typeId;

    @ApiModelProperty("课程销售价格，设置为0则可免费观看")
    @TableField(value = "price")
    private BigDecimal price;

    @ApiModelProperty("总课时")
    @TableField(value = "lesson_num")
    private Integer lessonNum;

    @ApiModelProperty("销售数量")
    @TableField(value = "sale_count")
    private Long saleCount;

    @ApiModelProperty("浏览数量")
    @TableField(value = "view_count")
    private Long viewCount;

    @ApiModelProperty("乐观锁")
    @TableField(value = "version")
    private Long version;

    @ApiModelProperty("课程状态 0未发布 1已发布")
    @TableField(value = "status")
    private Integer status;

    @ApiModelProperty("逻辑删除 1（true）已删除， 0（false）未删除")
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
