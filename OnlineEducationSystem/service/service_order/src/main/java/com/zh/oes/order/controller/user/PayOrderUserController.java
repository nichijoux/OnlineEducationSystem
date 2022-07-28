package com.zh.oes.order.controller.user;


import com.zh.oes.common.utils.Result;
import com.zh.oes.model.entity.order.PayOrder;
import com.zh.oes.order.service.PayOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-26
 */
@Api(tags = "订单前台api接口")
@RestController
@RequestMapping("user/order/payOrder")
public class PayOrderUserController {
    private PayOrderService orderService;

    @Autowired
    public void setOrderService(PayOrderService orderService) {
        this.orderService = orderService;
    }

    @ApiOperation(value = "生成订单,需要课程信息和用户信息")
    @PostMapping("generateOrder/{courseId}")
    public Result generateOrder(
            @ApiParam(name = "courseId", value = "课程id", required = true)
            @PathVariable Long courseId,
            @ApiParam(name = "request", value = "带有用户cookie信息的请求", required = true)
                    HttpServletRequest request) {
        Long orderNo = orderService.generateOrder(courseId,request);
        return Result.success(orderNo);
    }

    @ApiOperation(value = "根据订单号,查询订单信息")
    @GetMapping("getOrderInfo/{orderNo}")
    public Result getOrderInfo(
            @ApiParam(name = "orderId",value = "订单id",required = true)
            @PathVariable Long orderNo){
        PayOrder order = orderService.getOrderInfo(orderNo);
        return Result.success(order);
    }

    @ApiOperation(value = "远程调用方法,前端不要直接使用,根据课程id和用户id查询用户是否购买课程")
    @GetMapping("remoteGetUserHaveBuyCourse/{courseId}/{memberId}")
    public Boolean remoteGetUserHaveBuyCourse(
            @ApiParam(name = "courseId", value = "课程id", required = true)
            @PathVariable Long courseId,
            @ApiParam(name = "memberId", value = "用户id", required = true)
            @PathVariable Long memberId) {
        return orderService.haveBuyCourse(courseId, memberId);
    }
}

