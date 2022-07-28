package com.zh.oes.model.entity.statistics;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 网站统计日数据
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-26
 */
@Data
@TableName("statistics_daily")
@ApiModel(value = "StatisticsDaily对象", description = "网站统计日数据")
public class StatisticsDaily implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("统计日期")
    @TableField(value = "date_calculated")
    private String dateCalculated;

    @ApiModelProperty("注册人数")
    @TableField(value = "register_num")
    private Integer registerNum;

    @ApiModelProperty("登录人数")
    @TableField(value = "login_num")
    private Integer loginNum;

    @ApiModelProperty("每日播放视频数")
    @TableField(value = "video_view_num")
    private Integer videoViewNum;

    @ApiModelProperty("每日新增课程数")
    @TableField(value = "course_num")
    private Integer courseNum;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
