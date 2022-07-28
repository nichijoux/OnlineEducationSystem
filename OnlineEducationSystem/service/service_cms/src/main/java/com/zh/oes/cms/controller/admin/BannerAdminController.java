package com.zh.oes.cms.controller.admin;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.oes.cms.service.BannerService;
import com.zh.oes.common.utils.Result;
import com.zh.oes.model.entity.cms.Banner;
import com.zh.oes.model.vo.cms.BannerQueryCondition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 首页banner表 前端控制器
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-25
 */
@Api(tags = "banner后台api接口")
@RestController
@RequestMapping("admin/cms/banner")
public class BannerAdminController {
    private BannerService bannerService;

    @Autowired
    public void setBannerService(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @ApiOperation(value = "分页查询banner信息")
    @PostMapping("/pageQueryBanner/{index}/{limit}")
    public Result pageQueryBanner(
            @ApiParam(name = "index", value = "当前页", required = true)
            @PathVariable("index") Long index,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable("limit") Long limit,
            @ApiParam(name = "queryCondition", value = "banner查询条件")
            @RequestBody(required = false) BannerQueryCondition queryCondition) {
        Page<Banner> bannerList = bannerService.pageQueryBanner(index, limit, queryCondition);
        return Result.success(bannerList);
    }

    @ApiOperation(value = "根据id获取banner信息")
    @GetMapping("getBanner/{bannerId}")
    public Result getBanner(
            @ApiParam(name = "bannerId", value = "要查询的banner的Id", required = true)
            @PathVariable Long bannerId) {
        Banner banner = bannerService.getById(bannerId);
        return banner != null ?
                Result.success(banner) : Result.failure().message("查找banner不存在");
    }

    @ApiOperation(value = "添加banner信息")
    @PostMapping("addBanner")
    public Result addBanner(
            @ApiParam(name = "Banner", value = "要加入的banner数据", required = true)
            @Validated @RequestBody Banner banner) {
        return bannerService.save(banner) ?
                Result.success() : Result.failure().message("添加banner失败");
    }

    @ApiOperation(value = "更新banner信息")
    @PostMapping("updateBanner")
    public Result updateBanner(
            @ApiParam(name = "banner", value = "要添加的banner数据", required = true)
            @Validated @RequestBody Banner banner) {
        return bannerService.updateById(banner) ?
                Result.success() : Result.failure().message("修改banner信息失败");
    }

    @ApiOperation(value = "禁用或者启用某个banner")
    @PostMapping("enableOrDisableBanner/{bannerId}")
    public Result enableOrDisableBanner(
            @ApiParam(name = "bannerId", value = "要禁用或启用的bannerId", required = true)
            @PathVariable Long bannerId,
            @ApiParam(name = "isEnable", value = "是否启用某个banner", required = true)
            @RequestParam("isEnable") Boolean isEnable) {
        Banner banner = new Banner();
        banner.setIsEnable(isEnable);
        banner.setId(bannerId);
        return bannerService.updateById(banner) ?
                Result.success() : Result.failure().message("禁用或启用某banner失败");
    }

    @ApiOperation(value = "根据banner id删除banner数据")
    @DeleteMapping("deleteBanner/{bannerId}")
    public Result deleteBanner(
            @ApiParam(name = "bannerId", value = "要删除的bannerId", required = true)
            @PathVariable Long bannerId) {
        return bannerService.removeById(bannerId) ?
                Result.success() : Result.failure().message("删除banner失败");
    }
}

