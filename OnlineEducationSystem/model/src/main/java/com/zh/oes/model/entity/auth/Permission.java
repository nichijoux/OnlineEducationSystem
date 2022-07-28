package com.zh.oes.model.entity.auth;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 权限
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("auth_permission")
@ApiModel(value = "Permission对象", description = "权限对象,对应auth_permission表")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "权限id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "所属父权限id")
    @TableField(value = "pid")
    private Long pid;

    @ApiModelProperty(value = "名称")
    @NotBlank(message = "权限名不能为空")
    @TableField(value = "name")
    private String name;

    @ApiModelProperty(value = "类型(1:菜单,2:按钮)")
    @Min(value = 1, message = "权限类型错误")
    @Max(value = 2, message = "权限类型错误")
    @TableField(value = "type")
    private Integer type;

    @ApiModelProperty(value = "权限值")
    @TableField(value = "permission_value")
    private String permissionValue;

    @ApiModelProperty(value = "访问路径")
    @TableField(value = "path")
    private String path;

    @ApiModelProperty(value = "组件路径")
    @TableField(value = "component")
    private String component;

    @ApiModelProperty(value = "重定向地址")
    @TableField(value = "redirect")
    private String redirect;

    @ApiModelProperty(value = "图标")
    @TableField(value = "icon")
    private String icon;

    @ApiModelProperty(value = "是否隐藏路由1（true）隐藏，0（false）不隐藏")
    @TableField(value = "hidden")
    private Boolean hidden;

    @ApiModelProperty(value = "菜单排序")
    @TableField(value = "sort")
    private Integer sort;

    @ApiModelProperty(value = "是否启用（1：启用，0：禁止）")
    @TableField(value = "is_enable")
    private Boolean isEnable;

    @ApiModelProperty(value = "创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @ApiModelProperty(value = "层级,当前为第几层")
    @TableField(exist = false)
    private Integer level;

    @ApiModelProperty(value = "子权限")
    @TableField(exist = false)
    private List<Permission> children;

    @ApiModelProperty(value = "是否选中,用于后台显示")
    @TableField(exist = false)
    private boolean isSelect;
}
