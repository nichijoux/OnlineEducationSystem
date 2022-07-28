package com.zh.oes.model.entity.edu;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 课程视频
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-23
 */
@Data
@TableName("edu_video")
@ApiModel(value = "Video对象", description = "课程视频")
public class Video implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("视频ID")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @ApiModelProperty("课程ID")
    @NotNull(message = "小节所属课程id不能为空")
    @TableField(value = "course_id")
    private Long courseId;

    @ApiModelProperty("章节ID")
    @NotNull(message = "小节所属章节id不能为空")
    @TableField(value = "chapter_id")
    private Long chapterId;

    @ApiModelProperty("小节(视频)名称")
    @NotBlank(message = "小节(视频)名称不能为空")
    @TableField(value = "title")
    private String title;

    @ApiModelProperty("云端视频资源")
    @TableField(value = "video_source_id")
    private String videoSourceId;

    @ApiModelProperty("原始文件名称")
    @TableField(value = "video_original_name")
    private String videoOriginalName;

    @ApiModelProperty("排序字段")
    @TableField(value = "sort")
    private Integer sort;

    @ApiModelProperty("播放次数")
    @TableField(value = "play_count")
    private Long playCount;

    @ApiModelProperty("是否可以试听：0收费 1免费")
    @TableField(value = "is_free")
    private Integer isFree;

    @ApiModelProperty("视频时长（秒）")
    @TableField(value = "duration")
    private Float duration;

    @ApiModelProperty("0未上传 1转码中  2正常")
    @TableField(value = "status")
    private Integer status;

    @ApiModelProperty("视频源文件大小（字节）")
    @TableField(value = "size")
    private Long size;

    @ApiModelProperty("乐观锁")
    @TableField(value = "version")
    private Long version;

    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}
