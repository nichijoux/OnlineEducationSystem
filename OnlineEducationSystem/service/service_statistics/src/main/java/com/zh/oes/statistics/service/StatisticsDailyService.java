package com.zh.oes.statistics.service;

import com.zh.oes.model.entity.statistics.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-26
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    // 统计注册人数
    void registerMemberCount(String day);

    // 生成统计数据
    Map<String, Object> showStatisticsData(String type, String begin, String end);
}
