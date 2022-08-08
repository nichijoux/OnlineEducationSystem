package com.zh.oes.model.vo.edu.admin;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel(value = "学科下载的VO对象", description = "学科下载的VO对象")
public class SubjectDownloadVO {
    @ExcelProperty("学科名")
    private String subject;

    @ExcelProperty("父学科名")
    private String parentSubject;
}
