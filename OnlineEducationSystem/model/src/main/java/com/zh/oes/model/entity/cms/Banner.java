package com.zh.oes.model.entity.cms;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 首页banner表
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-25
 */
@Data
@TableName("cms_banner")
@ApiModel(value = "Banner对象", description = "首页banner表")
public class Banner implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("标题")
    @NotBlank(message = "banner标题不能为空")
    @TableField(value = "title")
    private String title;

    @ApiModelProperty("图片地址")
    @TableField(value = "image_url")
    private String imageUrl;

    @ApiModelProperty("链接地址")
    @NotBlank(message = "链接地址不能为空")
    @TableField(value = "link_url")
    private String linkUrl;

    @ApiModelProperty("排序")
    @TableField(value = "sort")
    private Integer sort;

    @ApiModelProperty("是否启用 1（true）已启用，  0（false）已禁用")
    @TableField(value = "is_enable")
    private Boolean isEnable;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
