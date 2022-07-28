package com.zh.oes.model.entity.order;

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
 * 订单
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-26
 */
@Data
@TableName("pay_order")
@ApiModel(value = "PayOrder对象", description = "订单")
public class PayOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("订单号")
    @TableField(value = "order_no")
    private Long orderNo;

    @ApiModelProperty("课程id")
    @TableField(value = "course_id")
    private Long courseId;

    @ApiModelProperty("会员id")
    @TableField(value = "member_id")
    private Long memberId;

    @ApiModelProperty("课程名称")
    @TableField(value = "course_title")
    private String courseTitle;

    @ApiModelProperty("课程封面")
    @TableField(value = "course_cover")
    private String courseCover;

    @ApiModelProperty("讲师名称")
    @TableField(value = "teacher_name")
    private String teacherName;

    @ApiModelProperty("会员昵称")
    @TableField(value = "nickname")
    private String nickname;

    @ApiModelProperty("会员手机")
    @TableField(value = "mobile")
    private String mobile;

    @ApiModelProperty("订单金额（分）")
    @TableField(value = "total_fee")
    private BigDecimal totalFee;

    @ApiModelProperty("支付类型（1：微信 2：支付宝）")
    @TableField(value = "pay_type")
    private Integer payType;

    @ApiModelProperty("订单状态（0：未支付 1：已支付）")
    @TableField(value = "status")
    private Integer status;

    @ApiModelProperty("逻辑删除 1（true）已删除， 0（false）未删除")
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time",fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
