package com.zh.oes.order.controller.user;


import com.zh.oes.common.utils.Result;
import com.zh.oes.order.service.PayLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-26
 */
@Api(tags = "支付日志前台api接口")
@RestController
@RequestMapping("user/order/payLog")
public class PayLogUserController {
    private PayLogService logService;

    @Autowired
    public void setLogService(PayLogService logService) {
        this.logService = logService;
    }

    @ApiOperation(value = "根据订单id生成微信支付二维码")
    @GetMapping("generateOrCode/{orderNo}")
    public Result generateOrCode(
            @ApiParam(name = "orderNo", value = "订单id", required = true)
            @PathVariable Long orderNo) {
        Map<String,Object> OrCode = logService.generateOrCode(orderNo);
        return Result.success(OrCode);
    }

    @ApiOperation(value = "根据订单id查询支付状态")
    @GetMapping("queryOrderState/{orderNo}")
    public Result queryOrderState(
            @ApiParam(name = "orderNo", value = "订单id", required = true)
            @PathVariable Long orderNo) {
        Map<String, String> stateMap = logService.queryOrderStatus(orderNo);
        if (stateMap == null) {
            return Result.failure().message("支付出错");
        }
        if (stateMap.get("trade_state").equals("SUCCESS")) {
            // 更新支付表
            logService.updateOrderState(stateMap);
            return Result.success().message("支付成功");
        }
        return Result.success().message("正在支付").code(25000);
    }
}

