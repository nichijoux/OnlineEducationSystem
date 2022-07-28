package com.zh.oes.model.vo.auth;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "角色查询条件对象", description = "角色查询对象封装")
public class RoleQueryCondition implements Serializable {
    @ApiModelProperty(value = "角色名称,模糊查询")
    private String name;

    @ApiModelProperty(value = "查询开始时间", example = "2022-6-24 12:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String beginTime;

    @ApiModelProperty(value = "查询结束时间", example = "2022-6-24 12:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private String endTime;
}
