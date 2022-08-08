package com.zh.oes.model.entity.auth;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 角色权限
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("auth_role_permission")
@ApiModel(value = "RolePermission对象", description = "角色权限,对应auth_role_permission表")
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty(value = "角色id")
    @TableField(value = "role_id")
    private Long roleId;

    @ApiModelProperty(value = "权限id")
    @TableField(value = "permission_id")
    private Long permissionId;

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
}
