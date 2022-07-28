package com.zh.oes.model.entity.ucenter;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 会员表
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-25
 */
@Data
@TableName("student_member")
@ApiModel(value = "Member对象", description = "会员表")
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("会员id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("微信openid")
    @TableField(value = "openid")
    private String openid;

    @ApiModelProperty("手机号")
    @TableField(value = "mobile")
    private String mobile;

    @ApiModelProperty("密码")
    @TableField(value = "password")
    private String password;

    @ApiModelProperty("昵称")
    @TableField(value = "nickname")
    private String nickname;

    @ApiModelProperty("性别 1 女，2 男")
    @TableField(value = "sex")
    private Integer sex;

    @ApiModelProperty("年龄")
    @TableField(value = "age")
    private Integer age;

    @ApiModelProperty("用户头像")
    @TableField(value = "avatar")
    private String avatar;

    @ApiModelProperty("用户签名")
    @TableField(value = "sign")
    private String sign;

    @ApiModelProperty("是否启用 1（true）已启用，  0（false）已禁用")
    @TableField(value = "is_enable")
    private Boolean isEnable;

    @ApiModelProperty("逻辑删除 1（true）已删除， 0（false）未删除")
    @TableField(value = "is_deleted")
    private Boolean isDeleted;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
