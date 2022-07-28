package com.zh.oes.model.vo.edu.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@ApiModel(value = "评论VO", description = "前端评论要传递的vo类,部分数据是根据request请求头得到的,不需要传递")
public class CommentVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "课程id")
    @NotNull(message = "课程id不能为空")
    private Long courseId;

    @ApiModelProperty(value = "用户id")
    private Long memberId;

    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "评论内容")
    @NotBlank(message = "评论内容不能为空")
    private String content;

    @ApiModelProperty(value = "评论星级")
    @Min(value = 1, message = "评论最低为1星")
    @Min(value = 5, message = "评论最高为5星")
    private Integer star;
}