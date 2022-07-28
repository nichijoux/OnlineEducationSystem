package com.zh.oes.order.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.oes.model.entity.order.PayOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.oes.model.vo.order.OrderQueryCondition;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-26
 */
public interface PayOrderService extends IService<PayOrder> {
    // 根据课程id和用户信息生成订单
    Long generateOrder(Long courseId, HttpServletRequest request);

    // 根据订单号查询订单信息
    PayOrder getOrderInfo(Long orderNo);

    // 用户是否购买课程
    Boolean haveBuyCourse(Long courseId, Long memberId);

    // 分页查询获取订单信息
    Page<PayOrder> pageQueryOrder(Long index, Long limit, OrderQueryCondition queryCondition);
}
