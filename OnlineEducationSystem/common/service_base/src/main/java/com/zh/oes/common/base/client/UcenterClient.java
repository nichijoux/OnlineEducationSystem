package com.zh.oes.common.base.client;

import com.zh.oes.common.utils.Result;
import com.zh.oes.model.vo.edu.user.MemberVO;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Component
@FeignClient(name = "service-ucenter", fallback = UcenterClientHystrix.class)
public interface UcenterClient {

    @GetMapping("user/ucenter/member/remoteGetUser/{userId}")
    MemberVO remoteGetUser(
            @ApiParam(name = "userId", value = "用户id", required = true)
            @PathVariable("userId") Long userId);

    @GetMapping("admin/ucenter/member/remoteRegisterCount/{day}")
    Result remoteRegisterCount(
            @ApiParam(name = "day", value = "要统计的那一天", required = true)
            @PathVariable("day") String day);

    @GetMapping("admin/ucenter/member/remoteCountUser")
    Long remoteCountUser();
}
