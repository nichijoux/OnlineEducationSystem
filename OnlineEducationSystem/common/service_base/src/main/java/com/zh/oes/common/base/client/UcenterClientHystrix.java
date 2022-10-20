package com.zh.oes.common.base.client;

import com.zh.oes.common.base.exception.OESException;
import com.zh.oes.common.utils.Result;
import com.zh.oes.model.vo.edu.user.MemberVO;
import org.springframework.stereotype.Component;

@Component
public class UcenterClientHystrix implements UcenterClient {
    @Override
    public MemberVO remoteGetUser(Long userId) {
        throw new OESException("Ucenter服务器宕机");
    }

    @Override
    public Result remoteRegisterCount(String day) {
        throw new OESException("Ucenter服务器宕机");
    }

    @Override
    public Long remoteCountUser() {
        return 0L;
    }
}
