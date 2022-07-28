package com.zh.oes.model.vo.edu.admin;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "章节VO", description = "传输章节的VO类")
public class ChapterVO {
    @ApiModelProperty(value = "章节id")
    private Long id;

    @ApiModelProperty(value = "章节名称")
    private String title;

    @ApiModelProperty(value = "当前章节下的子小节")
    private List<VideoVO> children;
}