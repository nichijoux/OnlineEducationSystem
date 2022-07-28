package com.zh.oes.order.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.oes.common.utils.Result;
import com.zh.oes.model.entity.order.PayOrder;
import com.zh.oes.model.vo.order.OrderQueryCondition;
import com.zh.oes.order.service.PayOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "订单后台api接口")
@RestController
@RequestMapping("admin/order/payOrder")
public class PayOrderAdminController {

    private PayOrderService orderService;

    @Autowired
    public void setOrderService(PayOrderService orderService) {
        this.orderService = orderService;
    }

    @ApiOperation(value = "分页查询订单信息")
    @PostMapping("pageQueryOrder/{index}/{limit}")
    public Result pageQueryOrder(
            @ApiParam(name = "index", value = "当前页", required = true)
            @PathVariable Long index,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "queryCondition", value = "查询条件",required = true)
            @Validated @RequestBody OrderQueryCondition queryCondition) {
        Page<PayOrder> orderList = orderService.pageQueryOrder(index, limit, queryCondition);
        return Result.success(orderList);
    }
}
