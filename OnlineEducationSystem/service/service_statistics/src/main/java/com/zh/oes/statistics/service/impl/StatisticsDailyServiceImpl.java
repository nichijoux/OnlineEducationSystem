package com.zh.oes.statistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.oes.common.base.client.UcenterClient;
import com.zh.oes.common.utils.Result;
import com.zh.oes.model.entity.statistics.StatisticsDaily;
import com.zh.oes.statistics.mapper.StatisticsDailyMapper;
import com.zh.oes.statistics.service.StatisticsDailyService;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-26
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {
    private UcenterClient ucenterClient;

    @Autowired
    public void setUcenterClient(UcenterClient ucenterClient) {
        this.ucenterClient = ucenterClient;
    }

    /**
     * 统计注册人数
     *
     * @param day 要统计的那一天
     */
    @Override
    public void registerMemberCount(String day) {
        Result registerCount = ucenterClient.remoteRegisterCount(day);

        // 删除已存在的统计对象
        LambdaQueryWrapper<StatisticsDaily> dayQueryWrapper = new LambdaQueryWrapper<>();
        dayQueryWrapper.eq(StatisticsDaily::getDateCalculated, day);
        baseMapper.delete(dayQueryWrapper);

        // 获取统计信息
        Integer registerNum = (Integer) registerCount.getData();
        Integer loginNum = RandomUtils.nextInt(100, 200);
        Integer videoViewNum = RandomUtils.nextInt(100, 200);
        Integer courseNum = RandomUtils.nextInt(100, 200);

        //创建统计对象
        StatisticsDaily daily = new StatisticsDaily();
        daily.setRegisterNum(registerNum);
        daily.setLoginNum(loginNum);
        daily.setVideoViewNum(videoViewNum);
        daily.setCourseNum(courseNum);
        daily.setDateCalculated(day);

        //插入到数据库
        baseMapper.insert(daily);
    }

    /**
     * 生成统计数据
     *
     * @param type  统计类型
     * @param begin 开始时间
     * @param end   结束时间
     * @return 统计数据
     */
    @Override
    public Map<String, Object> showStatisticsData(String type, String begin, String end) {
        //条件查询
        //select type from 表 where between begin and end

        QueryWrapper<StatisticsDaily> queryWrapper = new QueryWrapper<>();
        queryWrapper.between("date_calculated", begin, end);
        //只需要查这两个字段，type有4种可能值，取决于前端查询什么，后台数据库就搜什么
        //而不需要搜其他字段，节省时间
        queryWrapper.select("date_calculated", type);

        // 查出来的list
        List<StatisticsDaily> dailyList = baseMapper.selectList(queryWrapper);

        // 封装两个List
        // 日期List
        List<String> dateList = new ArrayList<>();
        // 统计数据List
        List<Integer> countList = new ArrayList<>();

        // 封装到日期List(横轴)
        for (StatisticsDaily statisticsDaily : dailyList)
            dateList.add(statisticsDaily.getDateCalculated());

        switch (type) {
            case "register_num": {
                for (StatisticsDaily statisticsDaily : dailyList)
                    countList.add(statisticsDaily.getRegisterNum());
                break;
            }
            case "login_num": {
                for (StatisticsDaily statisticsDaily : dailyList)
                    countList.add(statisticsDaily.getLoginNum());
                break;
            }
            case "video_view_num": {
                for (StatisticsDaily statisticsDaily : dailyList)
                    countList.add(statisticsDaily.getVideoViewNum());
                break;
            }
            case "course_num": {
                for (StatisticsDaily statisticsDaily : dailyList)
                    countList.add(statisticsDaily.getCourseNum());
                break;
            }
            default:
                break;
        }

        // 最后封装到map
        Map<String, Object> map = new HashMap<>();
        map.put("dateList", dateList);
        map.put("countList", countList);
        // 返回map
        return map;
    }
}
