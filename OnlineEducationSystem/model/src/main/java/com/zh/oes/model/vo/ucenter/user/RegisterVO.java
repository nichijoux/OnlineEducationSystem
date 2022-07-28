package com.zh.oes.model.vo.ucenter.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "注册对象", description = "注册时前后端交互对象")
public class RegisterVO {
    @ApiModelProperty(value = "昵称")
    @NotBlank(message = "昵称不能为空")
    private String nickname;

    @ApiModelProperty(value = "手机号")
    @NotBlank(message = "手机号不能为空")
    private String mobile;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "验证码")
    @NotBlank(message = "验证码不能为空")
    private String code;
}
