package com.zh.oes.statistics.controller;


import com.zh.oes.common.utils.Result;
import com.zh.oes.statistics.service.StatisticsDailyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-26
 */
@Api(tags = "统计数据后台api接口")
@RestController
@RequestMapping("admin/statistics/daily")
public class StatisticsDailyController {
    private StatisticsDailyService statisticsDailyService;

    @Autowired
    public void setStatisticsDailyService(StatisticsDailyService statisticsDailyService) {
        this.statisticsDailyService = statisticsDailyService;
    }

    @ApiOperation(value = "统计注册人数")
    @PostMapping("registerMemberCount/{day}")
    public Result registerMemberCount(
            @ApiParam(name = "day", value = "生成日期", required = true)
            @PathVariable String day) {
        statisticsDailyService.registerMemberCount(day);
        return Result.success();
    }

    @ApiOperation(value = "显示统计数据的方法")
    @GetMapping("getStatisticsData/{type}/{begin}/{end}")
    public Result getStatisticsData(
            @ApiParam(name = "type", value = "要显示的类型", required = true)
            @PathVariable String type,
            @ApiParam(name = "begin", value = "开始日期", required = true)
            @PathVariable String begin,
            @ApiParam(name = "end", value = "结束日期", required = true)
            @PathVariable String end) {
        // 在前端统计图的折线图中，需要返回两个json数组，而在java中
        // json串这些都是字符串，在java中能对应前端json数组的是List集合
        // 又要返回两个List集合，所以采用map封装两个list，最后返回
        Map<String, Object> statisticsData = statisticsDailyService.showStatisticsData(type, begin, end);
        return Result.success(statisticsData);
    }
}

