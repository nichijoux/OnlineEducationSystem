package com.zh.oes.model.entity.edu;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@ApiModel(value = "层级学科", description = "LevelSubject可层层嵌套,用于表示n级科目")
public class LevelSubject {
    @ApiModelProperty(value = "学科id")
    @NotBlank(message = "学科id不能为空")
    private Long id;

    @ApiModelProperty(value = "学科名")
    @NotBlank(message = "学科名不能为空")
    private String title;

    @ApiModelProperty(value = "当前层数")
    private Integer level;

    //树形结构
    //一个一级分类包含1个或多个二级分类
    //data2: [{
    //        id: 1,
    //        title: '一级分类 1',
    //        children: [{
    //          id: 4,
    //          title: '二级分类 1-1',
    //          children : null
    //        }
    @ApiModelProperty(value = "子科目")
    private List<LevelSubject> children;
}
