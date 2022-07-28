package com.zh.oes.common.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 统一返回结果
 */
@Data
public class Result {
    private static final Integer SUCCESS = 20000;
    private static final Integer FAILURE = 20001;

    /**
     * 用户未认证应该返回的状态码
     */
    public static final Integer USER_AUTH_CODE = 28000;

    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Object data;

    //把构造方法私有
    private Result() {
    }

    //成功静态方法
    public static Result success() {
        Result r = new Result();
        r.setSuccess(true);
        r.setCode(SUCCESS);
        r.setMessage("成功");
        return r;
    }

    public static Result success(Object data) {
        Result r = new Result();
        r.setSuccess(true);
        r.setCode(SUCCESS);
        r.setMessage("成功");
        r.setData(data);
        return r;
    }

    //失败静态方法
    public static Result failure() {
        Result r = new Result();
        r.setSuccess(false);
        r.setCode(FAILURE);
        r.setMessage("失败");
        return r;
    }

    // 链式编程
    public Result success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public Result message(String message) {
        this.setMessage(message);
        return this;
    }

    public Result code(Integer code) {
        this.setCode(code);
        return this;
    }

    public Result data(Object data) {
        this.data = data;
        return this;
    }
}
