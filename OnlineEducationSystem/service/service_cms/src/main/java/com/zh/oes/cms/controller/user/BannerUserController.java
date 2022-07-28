package com.zh.oes.cms.controller.user;

import com.zh.oes.cms.service.BannerService;
import com.zh.oes.common.utils.Result;
import com.zh.oes.model.entity.cms.Banner;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "banner前台api接口")
@RestController
@RequestMapping("user/cms/banner")
public class BannerUserController {
    private BannerService bannerService;

    @Autowired
    public void setBannerService(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @ApiOperation(value = "获取全部的banner数据")
    @GetMapping("getAllBanner")
    public Result getAllBanner(){
        List<Banner> bannerList = bannerService.getAllBanner();
        return Result.success(bannerList);
    }
}
