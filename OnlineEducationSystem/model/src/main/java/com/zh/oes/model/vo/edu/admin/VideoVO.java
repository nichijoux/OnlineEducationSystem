package com.zh.oes.model.vo.edu.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "视频VO", description = "传输的视频VO类")
public class VideoVO {
    @ApiModelProperty(value = "章节id")
    private Long id;

    @ApiModelProperty(value = "节点名称")
    private String title;

    @ApiModelProperty(value = "云端视频资源")
    private String videoSourceId;
}
