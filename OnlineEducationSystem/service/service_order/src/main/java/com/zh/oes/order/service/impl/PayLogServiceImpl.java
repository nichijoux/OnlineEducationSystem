package com.zh.oes.order.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.wxpay.sdk.WXPayUtil;
import com.zh.oes.common.base.exception.OESException;
import com.zh.oes.model.entity.order.PayLog;
import com.zh.oes.model.entity.order.PayOrder;
import com.zh.oes.order.mapper.PayLogMapper;
import com.zh.oes.order.service.PayLogService;
import com.zh.oes.order.service.PayOrderService;
import com.zh.oes.order.utils.ConstantPropertiesUtil;
import com.zh.oes.order.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 支付日志表 服务实现类
 * </p>
 *
 * @author nichijoux
 * @since 2022-07-26
 */
@Service
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLog> implements PayLogService {

    private PayOrderService orderService;

    @Autowired
    public void setOrderService(PayOrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 根据订单id生成微信支付二维码
     *
     * @param orderNo 订单id
     * @return 微信支付二维码
     */
    @Override
    public Map<String, Object> generateOrCode(Long orderNo) {
        // 1.根据订单id查询订单信息
        LambdaQueryWrapper<PayOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PayOrder::getOrderNo, orderNo);
        PayOrder order = orderService.getOne(wrapper);

        // 2.使用map设置生成二维码需要的参数
        Map<String, String> m = new HashMap<>();
        m.put("appid", ConstantPropertiesUtil.WX_PAY_APP_ID);
        m.put("mch_id", ConstantPropertiesUtil.WX_PAY_PARTNER);
        m.put("nonce_str", WXPayUtil.generateNonceStr());
        m.put("body", order.getCourseTitle());
        m.put("out_trade_no", String.valueOf(orderNo));
        m.put("total_fee", order.getTotalFee().multiply(new BigDecimal("100")).longValue() + "");
        m.put("spbill_create_ip", "127.0.0.1");
        m.put("notify_url", ConstantPropertiesUtil.WX_PAY_NOTIFY_URL + "\n");
        m.put("trade_type", "NATIVE");

        // 3.发送httpclient请求，需要传递xml格式的参数，微信支付提供的固定地址
        HttpClientUtil httpClient = new HttpClientUtil("https://api.mch.weixin.qq.com/pay/unifiedorder");

        // 3.1 client设置参数
        try {

            httpClient.setXmlParam(WXPayUtil.generateSignedXml(m, ConstantPropertiesUtil.WX_PAY_PARTNER_KEY));
            httpClient.setHttps(true);
            httpClient.post();

            // 3.2返回第三方的数据
            String xml = httpClient.getContent();
            Map<String, String> resultMap = WXPayUtil.xmlToMap(xml);

            // 3.3 封装最终返回的map
            Map<String, Object> map = new HashMap<>();
            map.put("out_trade_no", orderNo);
            map.put("course_id", order.getCourseId());
            map.put("total_fee", order.getTotalFee());
            //二维码地址
            map.put("result_code", resultMap.get("result_code"));
            map.put("code_url", resultMap.get("code_url"));

            //微信支付二维码2小时过期，可采取2小时未支付取消订单
            //redisTemplate.opsForValue().set(orderNo, map, 120, TimeUnit.MINUTES);

            // 4. 返回map
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            throw new OESException("生成二维码失败");
        }
    }

    /**
     * 查询订单状态
     *
     * @param orderNo 订单号
     * @return 订单状态
     */
    @Override
    public Map<String, String> queryOrderStatus(Long orderNo) {
        try {
            // 1、封装参数
            Map<String, String> data = new HashMap<>();
            data.put("appid", ConstantPropertiesUtil.WX_PAY_APP_ID);
            data.put("mch_id", ConstantPropertiesUtil.WX_PAY_PARTNER);
            data.put("out_trade_no", String.valueOf(orderNo));
            data.put("nonce_str", WXPayUtil.generateNonceStr());

            // 2、设置请求
            HttpClientUtil client = new HttpClientUtil("https://api.mch.weixin.qq.com/pay/orderquery");
            client.setXmlParam(WXPayUtil.generateSignedXml(data, ConstantPropertiesUtil.WX_PAY_PARTNER_KEY));
            client.setHttps(true);
            client.post();
            // 3、返回第三方的数据
            String xml = client.getContent();

            // 4、转成Map
            return WXPayUtil.xmlToMap(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 更新订单状态
     *
     * @param stateMap 状态map
     */
    @Override
    public void updateOrderState(Map<String, String> stateMap) {
//获取订单id
        String orderNo = stateMap.get("out_trade_no");

        // 根据订单id查询订单信息
        LambdaQueryWrapper<PayOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PayOrder::getOrderNo, orderNo);
        PayOrder order = orderService.getOne(wrapper);

        // 1为已支付，不需要更新
        if (order.getStatus() == 1) return;

        // 否则需要将其设置为1
        order.setStatus(1);
        // 更新t_order表中信息
        orderService.updateById(order);

        // 记录支付日志--开始
        PayLog payLog = new PayLog();
        // 支付订单号
        payLog.setOrderNo(order.getOrderNo());
        payLog.setPayTime(new Date());
        // 支付类型
        payLog.setPayType(1);
        // 总金额(分)
        payLog.setTotalFee(order.getTotalFee());
        // 支付状态
        payLog.setTradeState(stateMap.get("trade_state"));
        payLog.setTransactionId(stateMap.get("transaction_id"));
        // 其他属性
        payLog.setAttr(JSONObject.toJSONString(stateMap));

        //记录支付日志--结束
        baseMapper.insert(payLog);
    }
}
