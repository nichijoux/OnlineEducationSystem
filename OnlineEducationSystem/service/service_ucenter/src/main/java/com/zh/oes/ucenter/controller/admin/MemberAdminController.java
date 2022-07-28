package com.zh.oes.ucenter.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.oes.common.utils.Result;
import com.zh.oes.model.entity.ucenter.Member;
import com.zh.oes.model.vo.ucenter.admin.MemberQueryCondition;
import com.zh.oes.ucenter.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户后台api接口")
@RestController
@RequestMapping("admin/ucenter/member")
public class MemberAdminController {
    private MemberService memberService;

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    @ApiOperation(value = "分页查询用户")
    @PostMapping("pageQueryMember/{index}/{limit}")
    public Result pageQueryMember(
            @ApiParam(name = "index", value = "当前页", required = true)
            @PathVariable Long index,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @Validated @RequestBody(required = false) MemberQueryCondition queryCondition) {
        Page<Member> memberList = memberService.pageQueryMember(index, limit, queryCondition);
        // 密码全设空,防止被破解
        memberList.getRecords().forEach(item -> item.setPassword(""));
        return Result.success(memberList);
    }

    @ApiOperation(value = "禁用或者启用某个用户")
    @PostMapping("enableOrDisableMember/{userId}")
    public Result enableOrDisableMember(
            @ApiParam(name = "userId", value = "要禁用或启用的用户id", required = true)
            @PathVariable Long userId,
            @ApiParam(name = "isEnable", value = "是否启用某个用户", required = true)
            @RequestParam("isEnable") Boolean isEnable) {
        Member member = new Member();
        member.setIsEnable(isEnable);
        member.setId(userId);
        return memberService.updateById(member) ?
                Result.success() : Result.failure().message("禁用或启用用户失败");
    }

    @ApiOperation(value = "远程调用方法统计当日注册人数")
    @GetMapping("remoteRegisterCount/{day}")
    public Result remoteRegisterCount(
            @ApiParam(name = "day", value = "要统计的那一天", required = true)
            @PathVariable String day) {
        int registerCount = memberService.registerCount(day);
        return Result.success(registerCount);
    }
}
