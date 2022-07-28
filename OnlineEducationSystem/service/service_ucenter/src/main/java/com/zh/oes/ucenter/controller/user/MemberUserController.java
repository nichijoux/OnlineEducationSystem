package com.zh.oes.ucenter.controller.user;


import com.zh.oes.common.utils.JwtUtil;
import com.zh.oes.common.utils.Result;
import com.zh.oes.model.entity.ucenter.Member;
import com.zh.oes.model.vo.edu.user.MemberVO;
import com.zh.oes.model.vo.ucenter.user.LoginVO;
import com.zh.oes.model.vo.ucenter.user.PasswordVO;
import com.zh.oes.model.vo.ucenter.user.RegisterVO;
import com.zh.oes.ucenter.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-25
 */
@Api(tags = "用户后台api接口")
@RestController
@RequestMapping("user/ucenter/member")
public class MemberUserController {
    private MemberService memberService;

    @Autowired
    public void setMemberService(MemberService memberService) {
        this.memberService = memberService;
    }

    @ApiOperation(value = "用户登录")
    @PostMapping("userLogin")
    public Result userLogin(
            @ApiParam(name = "loginVO", value = "登录需要的数据", required = true)
            @Validated @RequestBody LoginVO loginVO) {
        String token = memberService.userLogin(loginVO);
        return Result.success(token);
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("userRegister")
    public Result userRegister(
            @ApiParam(name = "registerVO", value = "请求体", required = true)
            @Validated @RequestBody RegisterVO registerVO) {
        memberService.userRegister(registerVO);
        return Result.success();
    }

    @ApiOperation(value = "根据token获取用户信息")
    @GetMapping("getUserInfo")
    public Result getUserInfo(
            @ApiParam(name = "request", value = "请求体", required = true)
                    HttpServletRequest request) {
        // 调用jwt工具类，根据request对象获取头信息，返回用户id
        String memberIdByJwtToken = JwtUtil.getUserIdByJwtToken(request);
        // 根据用户id查询用户信息
        Member member = memberService.getById(memberIdByJwtToken);
        // 不显示密码,防止破解
        member.setPassword("");
        return Result.success(member);
    }

    @ApiOperation(value = "修改用户信息")
    @PostMapping("updateUserInfo")
    public Result updateUserInfo(
            @ApiParam(name = "member", value = "用户", required = true)
            @Validated @RequestBody Member updateMember,
            @ApiParam(name = "request", value = "请求体", required = true)
                    HttpServletRequest request) {
        // 调用jwt工具类，根据request对象获取头信息，返回用户id
        String memberIdByJwtToken = JwtUtil.getUserIdByJwtToken(request);
        // 根据用户id查询用户信息
        Member member = memberService.getById(memberIdByJwtToken);
        if (member == null) {
            return Result.failure().message("用户未登录,请重新登录").code(Result.USER_AUTH_CODE);
        }
        // 否则应该对比updateMember和member是否为同一个
        if (!updateMember.getId().equals(member.getId())) {
            return Result.failure().message("修改用户和当前登录用户不同,请重新登录").code(Result.USER_AUTH_CODE);
        }
        // 先获取密码再修改
        updateMember.setPassword(member.getPassword());
        return memberService.updateById(updateMember) ?
                Result.success() : Result.failure();
    }

    // 更改密码
    @ApiOperation(value = "更改密码")
    @PostMapping("updatePassword")
    public Result updatePassword(
            @ApiParam(name = "passwordVO", value = "修改密码的实体", required = true)
            @Validated @RequestBody PasswordVO passwordVO,
            @ApiParam(name = "request", value = "请求体", required = true)
                    HttpServletRequest request) {
        // 调用jwt工具类，根据request对象获取头信息，返回用户id
        String memberIdByJwtToken = JwtUtil.getUserIdByJwtToken(request);
        // 根据用户id查询用户信息
        Member member = memberService.getById(memberIdByJwtToken);
        if (member == null) {
            return Result.failure().message("用户未登录,请重新登录").code(Result.USER_AUTH_CODE);
        }
        memberService.updatePassword(passwordVO, member);
        return Result.success().message("修改密码成功");
    }

    @ApiOperation(value = "远程调用方法根据id获取用户信息")
    @GetMapping("remoteGetUser/{userId}")
    public MemberVO remoteGetUser(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @PathVariable Long userId) {
        Member member = memberService.getById(userId);
        MemberVO memberVO = new MemberVO();
        BeanUtils.copyProperties(member, memberVO);
        return memberVO;
    }
}

