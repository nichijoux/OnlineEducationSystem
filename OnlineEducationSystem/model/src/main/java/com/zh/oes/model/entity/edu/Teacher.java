package com.zh.oes.model.entity.edu;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 讲师
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("edu_teacher")
@ApiModel(value = "Teacher对象", description = "讲师对象")
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "讲师ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "讲师姓名")
    @NotBlank(message = "讲师姓名不能为空")
    @TableField(value = "name")
    private String name;

    @ApiModelProperty(value = "讲师资历,一句话说明讲师")
    @NotBlank(message = "讲师资历不能为空")
    @TableField(value = "career")
    private String career;

    @ApiModelProperty(value = "讲师简介")
    @NotBlank(message = "讲师简介不能为空")
    @TableField(value = "intro")
    private String intro;

    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    @TableField(value = "level")
    private Integer level;

    @ApiModelProperty(value = "讲师头像")
    @TableField(value = "avatar")
    private String avatar;

    @ApiModelProperty(value = "排序")
    @TableField(value = "sort")
    private Integer sort;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
