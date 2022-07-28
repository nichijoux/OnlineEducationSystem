package com.zh.oes.ucenter.mapper;

import com.zh.oes.model.entity.ucenter.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-25
 */
@Mapper
public interface MemberMapper extends BaseMapper<Member> {
    // 注册人数统计
    Integer registerCount(String day);
}
