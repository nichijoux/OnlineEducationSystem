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
 * 支付日志表
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-26
 */
@Data
@TableName("pay_log")
@ApiModel(value = "PayLog对象", description = "支付日志表")
public class PayLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("订单号")
    @TableField(value = "order_no")
    private Long orderNo;

    @ApiModelProperty("支付完成时间")
    @TableField(value = "pay_time")
    private Date payTime;

    @ApiModelProperty("支付金额（分）")
    @TableField(value = "total_fee")
    private BigDecimal totalFee;

    @ApiModelProperty("交易流水号")
    @TableField(value = "transaction_id")
    private String transactionId;

    @ApiModelProperty("交易状态")
    @TableField(value = "trade_state")
    private String tradeState;

    @ApiModelProperty("支付类型（1：微信 2：支付宝）")
    @TableField(value = "pay_type")
    private Integer payType;

    @ApiModelProperty("其他属性")
    @TableField(value = "attr")
    private String attr;

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
