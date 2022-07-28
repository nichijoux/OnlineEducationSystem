package com.zh.oes.order.service;

import com.zh.oes.model.entity.order.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 支付日志表 服务类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-26
 */
public interface PayLogService extends IService<PayLog> {

    // 根据订单id生成微信支付二维码
    Map<String, Object> generateOrCode(Long orderNo);

    // 查询订单状态
    Map<String, String> queryOrderStatus(Long orderNo);

    // 更新订单状态
    void updateOrderState(Map<String, String> stateMap);
}
