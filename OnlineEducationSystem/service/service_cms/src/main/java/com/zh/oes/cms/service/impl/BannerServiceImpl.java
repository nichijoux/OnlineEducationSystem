package com.zh.oes.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.oes.cms.mapper.BannerMapper;
import com.zh.oes.cms.service.BannerService;
import com.zh.oes.model.entity.cms.Banner;
import com.zh.oes.model.vo.cms.BannerQueryCondition;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-25
 */
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {
    /**
     * 分页查询banner信息,并按sort排序
     *
     * @param index          当前页
     * @param limit          每页记录数
     * @param queryCondition 查询条件
     * @return 分页查询得到的banner数据
     */
    @Override
    public Page<Banner> pageQueryBanner(Long index, Long limit, BannerQueryCondition queryCondition) {
        Page<Banner> bannerPage = new Page<>(index, limit);
        Page<Banner> bannerList;
        if (queryCondition == null) {
            bannerList = baseMapper.selectPage(bannerPage, null);
        } else {
            LambdaQueryWrapper<Banner> wrapper = new LambdaQueryWrapper<>();
            // 获取查询条件
            String title = queryCondition.getTitle();
            Boolean isEnable = queryCondition.getIsEnable();
            String beginTime = queryCondition.getBeginTime();
            String endTime = queryCondition.getEndTime();
            // 设定查询条件
            wrapper.like(StringUtils.hasText(title), Banner::getTitle, title);
            wrapper.eq(Objects.nonNull(isEnable), Banner::getIsEnable, isEnable);
            wrapper.ge(StringUtils.hasText(beginTime), Banner::getCreateTime, beginTime);
            wrapper.le(StringUtils.hasText(endTime), Banner::getCreateTime, endTime);
            // 排序
            wrapper.orderByDesc(Banner::getSort);
            wrapper.orderByDesc(Banner::getCreateTime);
            // 查找数据
            bannerList = baseMapper.selectPage(bannerPage, wrapper);
        }
        return bannerList;
    }

    /**
     * 获取所有的,未被禁止的banner数据
     *
     * @return 所有可显示的banner列表
     */
    @Cacheable(value = "banner", key = "'getAllBanner'")
    @Override
    public List<Banner> getAllBanner() {
        LambdaQueryWrapper<Banner> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Banner::getIsEnable, true);
        return baseMapper.selectList(wrapper);
    }
}
