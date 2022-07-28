package com.zh.oes.order.mapper;

import com.zh.oes.model.entity.order.PayLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 支付日志表 Mapper 接口
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-26
 */
@Mapper
public interface PayLogMapper extends BaseMapper<PayLog> {

}
