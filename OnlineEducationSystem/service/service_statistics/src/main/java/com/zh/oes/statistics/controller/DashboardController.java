package com.zh.oes.statistics.controller;

import com.zh.oes.common.base.client.EduClient;
import com.zh.oes.common.base.client.OrderClient;
import com.zh.oes.common.base.client.UcenterClient;
import com.zh.oes.common.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "后台管理首页数据")
@RestController
@RequestMapping("admin/statistics/dashboard")
public class DashboardController {

    private UcenterClient ucenterClient;

    private EduClient eduClient;

    private OrderClient orderClient;

    @Autowired
    public void setUcenterClient(UcenterClient ucenterClient) {
        this.ucenterClient = ucenterClient;
    }

    @Autowired
    public void setCourseClient(EduClient eduClient) {
        this.eduClient = eduClient;
    }

    @Autowired
    public void setOrderClient(OrderClient orderClient) {
        this.orderClient = orderClient;
    }

    @ApiOperation("获取统计数据,包括注册人数、课程数、订单数、评论数")
    @GetMapping("getStatisticsData")
    public Result getStatisticsData() {
        Long userCount = ucenterClient.remoteCountUser();
        Long courseCount = eduClient.remoteCountCourse();
        Long orderCount = orderClient.remoteCountOrder();
        Long commentCount = eduClient.remoteCountComment();
        Map<String, Long> data = new HashMap<>();
        data.put("userCount", userCount);
        data.put("courseCount", courseCount);
        data.put("orderCount", orderCount);
        data.put("commentCount", commentCount);
        return Result.success(data);
    }
}
